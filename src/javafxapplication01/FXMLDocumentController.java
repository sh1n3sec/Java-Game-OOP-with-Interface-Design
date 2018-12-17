package javafxapplication01;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FXMLDocumentController implements Initializable {

    int iGlobal;

    static boolean hintButtonPressed = false;

    static boolean volButtonPressed = false;

    private int currentScore;

    @FXML
    private GridPane gridPane;

    @FXML
    public Button buttonHint;

    @FXML
    private Label scoreBar;

    @FXML
    private Button buttonSair;

    public botoes[] btns = new botoes[64];

    private EsqueletoJogo gameLogic = new EsqueletoJogo();

    @FXML
    private Label labelJogadas;

    @FXML
    private Label wonLabel;

    @FXML
    private Label overLabel;

    @FXML
    private Label hintOverLabel;

    @FXML
    private TitledPane acabaGanhar;

    @FXML
    private Label acabarJogo;

    @FXML
    private Label acabarJogo1;
    @FXML
    private Label labelSairMesmo;

    @FXML
    private Button botaoSimGanhar;

    @FXML
    private Button botaoNaoGanhar;

    @FXML
    private Button botaoSimGanhar1;

    @FXML
    private Button botaoNaoGanhar1;

    @FXML
    private Button finalButon;

    @FXML
    private ImageView imgstart;

    @FXML
    public Button buttonStart;

    @FXML
    private ImageView imgVol;

    @FXML
    private ImageView imgMute;

    @FXML
    private Button buttonSom;

    @FXML
    private ImageView imgVol1;

    @FXML
    private ImageView imgMute1;

    @FXML
    private Button buttonSom1;

    @FXML
    private Label hintLabel;

    public int px1, py1, px2, py2;

    public int selOneOrTwo = 1;

    int contadorVol = 1;

    int contadorVol2 = 1;

    URL resource = getClass().getResource("/sons/som1.mp3");
    Media media = new Media(resource.toString());
    MediaPlayer mediaP = new MediaPlayer(media);

    URL resource2 = getClass().getResource("/sons/som2.mp3");
    Media media2 = new Media(resource2.toString());
    MediaPlayer mediaP2 = new MediaPlayer(media2);

    URL resource3 = getClass().getResource("/sons/som3.mp3");
    Media media3 = new Media(resource3.toString());
    MediaPlayer mediaP3 = new MediaPlayer(media3);

    URL resource4 = getClass().getResource("/sons/som4.mp3");
    Media media4 = new Media(resource4.toString());
    MediaPlayer mediaP4 = new MediaPlayer(media4);

    URL resource5 = getClass().getResource("/sons/som5.mp3");
    Media media5 = new Media(resource5.toString());
    MediaPlayer mediaP5 = new MediaPlayer(media5);

    URL resource7 = getClass().getResource("/sons/som7.mp3");
    Media media7 = new Media(resource7.toString());
    MediaPlayer mediaP7 = new MediaPlayer(media7);

    final String IDLE_BUTTON_STYLE = "-fx-rotate: 10";

    @FXML
    void handleButtonAction(ActionEvent event) {

        if (event.getSource() == buttonStart) {
            imgstart.setVisible(false);
            buttonStart.setVisible(false);
            mediaP.stop();
            mediaP5.play();
        }
        if (event.getSource() == buttonSom1) {

            contadorVol++;

            if ((contadorVol % 2) == 0) { // quando se carrega a segunda vez no botão do Volume, para dar mute á musica.
                imgMute1.setVisible(true);
                mediaP5.pause();
                return;
            } else {
                imgMute1.setVisible(false);
                mediaP5.play();
                return;
            }

        }
        if (event.getSource() == buttonSom) {

            contadorVol2++;

            if ((contadorVol2 % 2) == 0) { // quando se carrega a segunda vez no botão do Volume, para dar mute aos sound effects.
                imgMute.setVisible(true);
                mediaP2.setMute(true);
                mediaP3.setMute(true);
                mediaP4.setMute(true);
                mediaP7.setMute(true);
                return;
            } else {
                imgMute.setVisible(false);
                mediaP2.setMute(false);
                mediaP3.setMute(false);
                mediaP4.setMute(false);
                mediaP7.setMute(false);
                return;
            }

        }

        if (event.getSource() == buttonSair) {
            botaoSimGanhar1.setDisable(false);
            botaoNaoGanhar1.setDisable(false);
            labelSairMesmo.setOpacity(1.0);
            wonLabel.setOpacity(0.0);
            overLabel.setOpacity(0.0);
            acabarJogo.setOpacity(0.0);
            acabarJogo1.setOpacity(0.0);
            botaoSimGanhar.setDisable(true);
            botaoNaoGanhar.setDisable(true);
            acabaGanhar.setVisible(true);

        }
        if (event.getSource() == finalButon) {
            gameLogic.hints();

            if (gameLogic.ajuda > 0) {
                labelJogadas.setOpacity(1.0);
                labelJogadas.setText("Ainda tem jogadas possíveis! Continue!");
                return;
            }
            if (gameLogic.ajuda == 0) {
                labelJogadas.setOpacity(1.0);
                labelJogadas.setText("Já não há jogadas possíveis!");
                gameOver();
            }
        }
        if (event.getSource() == buttonHint) {

            //mediaP7.setVolume(20.0);
            mediaP7.stop();
            mediaP7.play();

            if (hintButtonPressed) { // quando se carrega a segunda vez no botão das Hints
                labelJogadas.setOpacity(0.0);
                hintOverLabel.setOpacity(1.0);
                return;
            }

            gameLogic.hints();
            labelJogadas.setOpacity(1.0);
            labelJogadas.setText("Ainda tem " + gameLogic.ajuda + " jogadas possíveis!");
            if (gameLogic.ajuda == 0) {
                gameOver();
            } else {
                btns[gameLogic.posi2].setStyle("-fx-border-color: #e60000;" + "-fx-border-width: 5;" + "-fx-background-image: url('/imagens/gem" + gameLogic.getPeca(gameLogic.posi2) + ".png')");
            }
            hintLabel.setText("Ainda tem " + gameLogic.hintz + " Disponíveis!");

            gameLogic.valores1.clear();

            if (gameLogic.hintz == 0) {
                buttonHint.setDisable(true);
                hintLabel.setText("Já não tem mais hints disponiveis!");
                finalButon.setDisable(false);
                finalButon.setOpacity(1.0);

            }
            gameLogic.hintz--;

        }

        if (event.getSource()
                == scoreBar) {

        }
        if (event.getSource()
                == gridPane) {

        }
        if (event.getSource()
                == wonLabel) {

        }
        if (event.getSource()
                == overLabel) {

        }
        if (event.getSource()
                == hintOverLabel) {

        }
        if (event.getSource() == botaoSimGanhar) {

            System.out.println("OK");
            labelJogadas.setOpacity(0.0);
            recomeçar();

        }

        if (event.getSource() == botaoNaoGanhar) {

            System.exit(0);
        }
        if (event.getSource() == botaoSimGanhar1) { //botao sair
            System.exit(0);
        }
        if (event.getSource() == botaoNaoGanhar1) { //botao sair
            acabaGanhar.setVisible(false);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mediaP.setCycleCount(MediaPlayer.INDEFINITE);
        mediaP.play();
        gameLogic.hintz--;
        acabaGanhar.setVisible(false);
        gameLogic.getValores();
        gameLogic.copyArrayToArrayAux();
        gameLogic.verificarLinha();
        gameLogic.verificarColuna();
        gameLogic.verificarNova();
        gameLogic.imprimeAux();
        initBtnsArray();
        finalButon.setDisable(true);
        finalButon.setOpacity(0.0);

    }

    private void initBtnsArray() {

        // Random myRandomNumber = new Random();
        System.out.println(btns.length);
        for (iGlobal = 0; iGlobal < btns.length; iGlobal++) {
            gameLogic.verificarNova2();
            btns[iGlobal] = new botoes();
            btns[iGlobal].setPadding(Insets.EMPTY);
            btns[iGlobal].setMinSize(70, 70);
            btns[iGlobal].setMaxSize(70, 70);

            btns[iGlobal].setStyle("-fx-background-image: url('/imagens/gem" + (gameLogic.getPeca(iGlobal)) + ".png')");

            gridPane.add(btns[iGlobal], iGlobal % 8, (int) (iGlobal / 8)); //colunas, linhas
            gameLogic.verificarNova2();
            btns[iGlobal].setOnAction(this::movimentoPeca);

        }
    }

    private void updateBtnsArray() {

        //Random myRandomNumber = new Random();
        for (int i = 0; i < btns.length; i++) {

            btns[i].setStyle("-fx-background-image: url('/imagens/gem" + (gameLogic.getPeca(i)) + ".png')");

            //gridPane.add(btns[i], i % 8, (int) (i / 8));
        }

    }

    private void resetBtns() {
        //Random myRandomNumber = new Random();
        for (int i = 0; i < btns.length; i++) {
            if (btns[i].isRodar()) {

                btns[i].setRotate(0.0);
                btns[i].setRodar(false);

            }

        }

    }

    void movimentoPeca(ActionEvent event) {

        for (int i = 0; i < btns.length; i++) {
            if (event.getSource() == btns[i]) {
                btns[i].setRotate(20.0);
                btns[i].setRodar(true);
                if (selOneOrTwo == 1) {
                    System.out.print("First button clicked: ");
                    py1 = i % 8;
                    px1 = i / 8;
                    System.out.println("px1=" + px1 + " py1=" + py1);
                    selOneOrTwo = 2;

                } else if (selOneOrTwo == 2) {
                    System.out.print("Second button clicked: ");

                    py2 = i % 8;
                    px2 = i / 8;
                    System.out.println("px2=" + px2 + " py2=" + py2);
                    selOneOrTwo = 1;

                    resetBtns();
                }

            }
        }
        if ((selOneOrTwo == 1) && (Math.abs(px1 - px2) + Math.abs(py1 - py2) == 1)) {

            System.out.println("Movimento adjacente");

            executarMovimento();

            gameLogic.verificarNova();
            updateBtnsArray();
            //mediaP4.play();
            labelJogadas.setOpacity(0.0);
            hintOverLabel.setOpacity(0.0);
            hintButtonPressed = false;

        } else if (selOneOrTwo == 1) {

            System.out.println("Erro! Jogada inválida!");
        }

    }

    private void executarMovimento() {

        System.out.println();
        System.out.println("executar");

        int auxVar = gameLogic.getArrayCord(px1, py1);

        gameLogic.setArrayCord(px1, py1, gameLogic.getArrayCord(px2, py2));

        gameLogic.setArrayCord(px2, py2, auxVar);

        //gameLogic.verificarNova2();
        if (!gameLogic.verificarNova2()) {
            System.out.println("Não forma mais de 3 elementos repetidos!");
            auxVar = gameLogic.getArrayCord(px1, py1);
            gameLogic.setArrayCord(px1, py1, gameLogic.getArrayCord(px2, py2));
            gameLogic.setArrayCord(px2, py2, auxVar);
            return;

        }

        gameLogic.reporValores();
        gameLogic.verificarNova2();
        gameLogic.imprimeAux();
        System.out.println("" + gameLogic.score);
        updateScore();

    }

    private void updateScore() {

        mediaP4.stop();
        mediaP4.play();
        currentScore = gameLogic.getScore();

        scoreBar.setText(String.valueOf(currentScore));

        if (currentScore >= 10000) {

            //mediaP2.setCycleCount(MediaPlayer.INDEFINITE);
            mediaP5.stop();
            mediaP2.play();
            imgMute.setVisible(false);
            buttonSom.setDisable(true);
            wonLabel.setOpacity(1.0);
            overLabel.setOpacity(0.0);
            acabarJogo.setOpacity(0.0);
            acabarJogo1.setOpacity(1.0);
            labelSairMesmo.setOpacity(0.0);
            botaoSimGanhar1.setDisable(true);
            botaoNaoGanhar1.setDisable(true);
            botaoSimGanhar.setDisable(false);
            botaoNaoGanhar.setDisable(false);
            acabaGanhar.setVisible(true);
            buttonHint.setDisable(true);
            finalButon.setDisable(true);

        }
    }

    private void recomeçar() {

        contadorVol = 1;
        mediaP2.stop();
        mediaP5.play();
        buttonSom.setDisable(false);
        imgMute.setVisible(false);
        gameLogic.hintz = 4;
        gameLogic.setScore(0); //utilizamos getters e setters para atualizar o score
        currentScore = gameLogic.getScore();
        hintButtonPressed = false;
        scoreBar.setText(String.valueOf(currentScore));
        wonLabel.setVisible(true);
        overLabel.setVisible(true);
        gameLogic.inicializaTabuleiro();
        acabaGanhar.setVisible(false);
        buttonHint.setDisable(false);
        finalButon.setDisable(true);
        finalButon.setOpacity(0.0);
        gameLogic.getValores();
        gameLogic.copyArrayToArrayAux();
        gameLogic.verificarLinha();
        gameLogic.verificarColuna();
        gameLogic.verificarNova();
        initBtnsArray();
        gameLogic.imprimeAux();

    }

    public void gameOver() {

        //mediaP3.setCycleCount(MediaPlayer.INDEFINITE);
        mediaP5.stop();
        mediaP3.play();
        imgMute.setVisible(false);
        buttonSom.setDisable(true);
        acabarJogo.setOpacity(1.0);
        acabarJogo1.setOpacity(1.0);
        labelSairMesmo.setOpacity(0.0);
        buttonHint.setDisable(true);
        finalButon.setDisable(true);
        botaoSimGanhar1.setDisable(true);
        botaoNaoGanhar1.setDisable(true);
        botaoSimGanhar.setDisable(false);
        botaoNaoGanhar.setDisable(false);
        buttonHint.setDisable(false);
        wonLabel.setOpacity(0.0);
        overLabel.setOpacity(1.0);
        acabaGanhar.setVisible(true);

    }

    @FXML
    private void rodarBotoes(MouseEvent t) {
//            System.out.println("X " + (t.getX()));
//            System.out.println("\nY " + (t.getY()));
//            Node shape = (Node) t.getSource();
//            shape.getTransforms().add(rotate(20.0, t.getX(), t.getY()));
//            //btns[iGlobal].setRotate(20.0);
//            btns[iGlobal].setRodar(true);
//            System.out.println(iGlobal + "  " + btns[iGlobal] + " " + btns[iGlobal].isRodar());
    }
}
