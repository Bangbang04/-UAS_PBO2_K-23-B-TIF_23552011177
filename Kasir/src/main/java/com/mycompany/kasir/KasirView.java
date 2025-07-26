
package com.mycompany.kasir;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class KasirView {
    private Stage primaryStage;
    private User user;
    private MenuOperations menuOps = new MenuOperations();
    private PesananOperations pesananOps = new PesananOperations();
    private ObservableList<Menu> menuList = FXCollections.observableArrayList();
    private ObservableList<Pesanan> pesananList = FXCollections.observableArrayList();

    public KasirView(Stage stage, User user) {
        this.primaryStage = stage;
        this.user = user;
        refreshData();
    }

    public BorderPane getView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(16));

        // Panel input pesanan baru
        VBox pesananBaruBox = new VBox(8);
        pesananBaruBox.setPadding(new Insets(8));
        pesananBaruBox.setStyle("-fx-background-color:#f8f9fa; -fx-background-radius:8;");
        TextField mejaField = new TextField(); mejaField.setPromptText("No. Meja");
        ComboBox<Menu> menuBox = new ComboBox<>(menuList); menuBox.setPromptText("Pilih Menu");
        TextField jumlahField = new TextField(); jumlahField.setPromptText("Jumlah");
        Button tambahBtn = new Button("Tambah ke Pesanan");

        ObservableList<DetailPesanan> detailTemp = FXCollections.observableArrayList();
        TableView<DetailPesanan> tempTable = new TableView<>(detailTemp);
        tempTable.setPrefHeight(100);
        TableColumn<DetailPesanan, String> namaCol = new TableColumn<>("Menu");
        namaCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getMenu().getNama()));
        TableColumn<DetailPesanan, Number> jumlahCol = new TableColumn<>("Jumlah");
        jumlahCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getJumlah()));
        TableColumn<DetailPesanan, Number> subCol = new TableColumn<>("Subtotal");
        subCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleDoubleProperty(cd.getValue().getSubTotal()));
        tempTable.getColumns().addAll(namaCol, jumlahCol, subCol);

        tambahBtn.setOnAction(e -> {
            Menu menu = menuBox.getValue();
            int jumlah = 1;
            try { jumlah = Integer.parseInt(jumlahField.getText()); } catch (Exception ex) { jumlah = 1; }
            if (menu != null && jumlah > 0) {
                detailTemp.add(new DetailPesanan(0, menu, jumlah));
                tempTable.refresh();
            }
        });

        Button simpanPesananBtn = new Button("Simpan Pesanan");
        simpanPesananBtn.setOnAction(e -> {
            if (mejaField.getText().isEmpty() || detailTemp.isEmpty()) {
                showError("Isi No. Meja dan menu pesanan!");
                return;
            }
            Pesanan pesanan = new Pesanan(0, mejaField.getText(), "Belum Bayar");
            for (DetailPesanan dp : detailTemp) pesanan.tambahDetail(dp);
            int id = pesananOps.buatPesanan(pesanan);
            if (id > 0) {
                showInfo("Pesanan berhasil disimpan.");
                detailTemp.clear();
                mejaField.clear();
                refreshData();
            }
        });

        pesananBaruBox.getChildren().addAll(
            new Label("Input Pesanan Baru"),
            mejaField, menuBox, jumlahField, tambahBtn,
            tempTable, simpanPesananBtn
        );

        // Panel daftar transaksi
        TableView<Pesanan> tabelPesanan = new TableView<>(pesananList);
        tabelPesanan.setPrefWidth(450);
        TableColumn<Pesanan, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getId()));
        TableColumn<Pesanan, String> mejaCol = new TableColumn<>("Meja");
        mejaCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getMeja()));
        TableColumn<Pesanan, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus()));
        TableColumn<Pesanan, Number> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().hitungTotal()));
        tabelPesanan.getColumns().addAll(idCol, mejaCol, statusCol, totalCol);

        // Detail pesanan transaksi
        TableView<DetailPesanan> detailTable = new TableView<>();
        detailTable.setPrefHeight(100);
        TableColumn<DetailPesanan, String> dNama = new TableColumn<>("Menu");
        dNama.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getMenu().getNama()));
        TableColumn<DetailPesanan, Number> dJumlah = new TableColumn<>("Jumlah");
        dJumlah.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getJumlah()));
        TableColumn<DetailPesanan, Number> dSub = new TableColumn<>("Subtotal");
        dSub.setCellValueFactory(cd -> new javafx.beans.property.SimpleDoubleProperty(cd.getValue().getSubTotal()));
        detailTable.getColumns().addAll(dNama, dJumlah, dSub);

        tabelPesanan.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
            if (val != null) detailTable.setItems(FXCollections.observableArrayList(val.getDetailList()));
            else detailTable.setItems(FXCollections.observableArrayList());
        });

        Button bayarBtn = new Button("Bayar Pesanan");
        bayarBtn.setOnAction(e -> {
            Pesanan selected = tabelPesanan.getSelectionModel().getSelectedItem();
            if (selected != null && !selected.getStatus().equals("Lunas")) {
                pesananOps.updateStatus(selected.getId(), "Lunas");
                showInfo("Pesanan sudah dibayar.");
                refreshData();
            }
        });

        VBox daftarBox = new VBox(8, new Label("Daftar Transaksi"), tabelPesanan, detailTable, bayarBtn);
        daftarBox.setPadding(new Insets(8));
        daftarBox.setStyle("-fx-background-color:#fff; -fx-background-radius:8;");

        HBox content = new HBox(24, pesananBaruBox, daftarBox);
        content.setAlignment(Pos.TOP_CENTER);

        root.setCenter(content);
        return root;
    }

    private void refreshData() {
        menuList.setAll(menuOps.getAllMenu());
        pesananList.setAll(pesananOps.getSemuaPesanan(menuList));
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
