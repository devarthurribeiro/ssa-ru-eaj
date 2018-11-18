package controle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import modelo.Alimento;
import modelo.ItemSolicitacao;
import modelo.Solicitacao;
import modelo.Usuario;
import util.AlertBox;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author arthur
 */
public class ControleSolicitacao implements Initializable {
    private Usuario usuarioLogado;
    private Solicitacao solicitacao = new Solicitacao();
    private List<Alimento> alimentos = new ArrayList<>();
    private ObservableList<ItemSolicitacao> itensSolicitcao = FXCollections.observableArrayList();
    @FXML
    private TableView<ItemSolicitacao> tabelaItens;
    @FXML
    private TableView<Solicitacao> tabelaSolicitacaoes;
    @FXML
    private TableColumn status;
    @FXML
    private JFXComboBox<Alimento> cbAlimentos;
    @FXML
    private JFXTextField txtQuantidade;
    @FXML
    private Label txtUsuario;
    @FXML
    private Label txtSetor;

    @FXML
    private void adicionarItem() {
        if (!cbAlimentos.getSelectionModel().isEmpty()) {
            Alimento alimento = cbAlimentos.getSelectionModel().getSelectedItem();
            int qtd = Integer.parseInt(txtQuantidade.getText());

            ItemSolicitacao itemA = new ItemSolicitacao(alimento, solicitacao, 1);
            int index = itensSolicitcao.indexOf(itemA);
            if (index >= 0) {
                itemA = itensSolicitcao.get(index);
                itemA.setQuantidade(itemA.getQuantidade() + qtd);
            } else {
                itensSolicitcao.add(itemA);
            }

            txtQuantidade.setText("1");
            tabelaItens.refresh();
        } else {
            new AlertBox("Selecione o item! \n ", "Erro", new Alert(Alert.AlertType.WARNING));
        }
    }

    @FXML
    private void removerItem() {
        if (!tabelaItens.getSelectionModel().isEmpty()) {
            int index = tabelaItens.getSelectionModel().getSelectedIndex();
            itensSolicitcao.remove(index);
            tabelaItens.refresh();
        } else {
            new AlertBox("Selecione o item! \n ", "Erro", new Alert(Alert.AlertType.WARNING));
        }
    }

    @FXML
    private void finalizarSolicitacao() {
        solicitacao.setSetor(usuarioLogado.getSetor());
        solicitacao.setUsuario(usuarioLogado);
        solicitacao.save();
        for (ItemSolicitacao item : itensSolicitcao) {
            item.save();
        }
        solicitacao.setItens(itensSolicitcao);
        new AlertBox("Solicitação enviada! \n ", "Sucesso", new Alert(Alert.AlertType.INFORMATION));
        solicitacao = new Solicitacao();
        itensSolicitcao.removeAll(itensSolicitcao);
        tabelaItens.refresh();
    }

    @FXML
    public void sair() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarAlimentos();
        tabelaItens.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item"));
        tabelaItens.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabelaItens.setItems(itensSolicitcao);
    }

    private void listarAlimentos() {
        ObservableList<Alimento> listaAlimentos = FXCollections.observableArrayList(Alimento.all());
        cbAlimentos.setItems(listaAlimentos);
    }

    private void listarSolicitacao() {
        ObservableList<Solicitacao> s = FXCollections.observableArrayList(Solicitacao.findByUsuario(usuarioLogado));
        tabelaSolicitacaoes.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tabelaSolicitacaoes.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("data"));
        tabelaSolicitacaoes.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("setor"));
        status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Solicitacao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Solicitacao, String> coluna) {
                SimpleStringProperty property = new SimpleStringProperty();
                property.setValue(coluna.getValue().isArquivada() ? "Atendida" : "Em aberto");
                return property;
            }
        });
        tabelaSolicitacaoes.setItems(s);

    }

    public void setUsuario(Usuario u) {
        usuarioLogado = u;
        listarSolicitacao();
        txtUsuario.setText(u.getNome());
        txtSetor.setText(u.getSetor().getNome());
    }
}
