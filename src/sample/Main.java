package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private  int NumeroA = 8;
    private  int NumeroB = 4;
    private int x = 900;
    private  int y = 430;
    private  int intentosx = 615;
    private  int intentosy = 295;
    private int ax = 590;
    int ay = 45;
    int lx = 660;
    int ly = 225;
    int qx = 760;
    int qy = 225;
    String modo;
    String modoJuego = "Facil";
    int valorDesafio = 18;
    Stage memoV;

    private numeros seleccion = null;
    private int contadorClic = 2;
    int contadorGeneral = 0;
    int contadorAcertado = 0;
    Label value = new Label("0");
    Label acertado = new Label("0");
    Button reiniciar = new Button("Reiniciar");
    Label act = new Label("0");
    Label vect = new Label("0");
    Button facil = new Button("Facil");
    Button medio = new Button("Medio");
    Button dificil = new Button("Paciencia extrema");
    Button duo = new Button("Du/o");
    Button desafioA = new Button();

    @Override
    public void start(Stage memorama) throws Exception {

        Pane panel = new Pane();
        panel.setPrefSize(900, 450);

        Stage inicio = new Stage();
        inicio.setTitle("Menu de inicio");
        inicio.setScene(new Scene(panel, 900, 450));
        inicio.setResizable(false);
        inicio.getIcons().add(new Image("file:src/pRecurso 2.png"));

        Rectangle rect = new Rectangle(0, 0, 915, 470);
        Image bgImage = new Image("file:src/pRecurso 2.png");
        rect.setFill(new ImagePattern(bgImage));


        facil.setLayoutX(90);
        facil.setLayoutY(92);
        facil.setStyle ( "-fx-background-color: transparent" );
        facil.setTextFill (Color.web ( "#fafbfd" ));

        medio.setLayoutX(85);
        medio.setLayoutY(165);
        medio.setStyle ( "-fx-background-color: transparent" );
        medio.setTextFill (Color.web ( "#fafbfd" ));

        dificil.setLayoutX(45);
        dificil.setLayoutY(230);
        dificil.setStyle ( "-fx-background-color: transparent" );
        dificil.setTextFill (Color.web ( "#fafbfd" ));

        duo.setLayoutX(45);
        duo.setLayoutY(290);
        duo.setStyle ( "-fx-background-color: BLACK" );
        duo.setTextFill (Color.web ( "#fafbfd" ));



        panel.getChildren().addAll(rect,facil,medio,dificil,duo);
        inicio.show();
        facil.setOnAction( __ ->
        {
            NumeroA = 8;
            NumeroB = 4;
            x = 900;
            y = 430;
            intentosx = 640;
            intentosy = 215;
            modoJuego = "Facil";
            inicio.close();
            construirMemorama(memorama);
        });

        medio.setOnAction( __ ->
        {
            NumeroA = 14;
            NumeroB = 7;
            x = 1366;
            y = 570;
            intentosx = 1000;
            intentosy = 345;
            ax = 1000;
            ay = 45;
            lx = 1020;
            ly = 375;
            qx = 1120;
            qy = 375;
            modoJuego = "medio";
            inicio.close();
            construirMemorama(memorama);
        });

        dificil.setOnAction( __ ->
        {
            NumeroA = 18;
            NumeroB = 12;
            x = 1366;
            y = 570;
            lx = 1020;
            ly = 375;
            intentosx = 1000;
            intentosy = 345;
            ax = 900;
            ay = 345;
            qx = 1120;
            qy = 375;
            modoJuego = "dificil";
            inicio.close();
            construirMemorama(memorama);
        });

        duo.setOnAction( __ ->
        {
            NumeroA = 18;
            NumeroB = 12;
            x = 1920;
            y = 560;
            lx = 1020;
            ly = 375;
            intentosx = 1000;
            intentosy = 345;
            ax = 900;
            ay = 345;
            qx = 1120;
            qy = 375;
            modoJuego = "duo";
            inicio.close();
            int puntajeJ1 = contadorGeneral/2;
            construirMemorama(memorama);
            if(memorama.isShowing()==true){
                memoV = memorama;
                System.out.println(valorDesafio);
            }

        });


        reiniciar.setOnAction(__ ->
        {

            memorama.close();
            Platform.runLater( () -> {
                try {
                    new Main().start( new Stage() );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } );


    }

    public void finJuego(Stage memorama){
        memorama.close();
    }

    public void construirMemorama(Stage memorama){
        memorama.setScene(new Scene(crearMemorama(NumeroA,  NumeroB,  x,  y, intentosx, intentosy,ax,ay,lx,ly,qx,qy,modoJuego)));
        memorama.setTitle("Memorama - Diseñado por: Daniel Matuz v0.1 - MODO " + modoJuego);
        memorama.getIcons().add(new Image("file:src/4.png"));
        memorama.setResizable(false);
        memorama.show();
    }

    public void construirMemoramaDual(Stage memoramaDual){
        memoramaDual.setScene(new Scene(crearMemorama(NumeroA,  NumeroB,  x,  y, intentosx, intentosy,ax,ay,lx,ly,qx,qy,modoJuego)));
        memoramaDual.setTitle("Memorama - Diseñado por: Daniel Matuz v0.1 - MODO " + modoJuego);
        memoramaDual.getIcons().add(new Image("file:src/4.png"));
        memoramaDual.setResizable(false);
        memoramaDual.show();
    }

    private Parent crearMemorama(int NumeroA, int NumeroB, int x, int y, int intentosx, int intentosy, int ax, int ay, int lx, int ly, int qx, int qy, String modoJuego) {


        //Image img = new Image("src/4.png");
        Image bgImage = new Image("file:src/2.gif");
        Pane root = new Pane();
        root.setPrefSize(x, y);
        Rectangle rect = new Rectangle(0, 0, x+16, y+25);
        rect.setFill(new ImagePattern(bgImage));
        reiniciar.setLayoutX(ax);
        reiniciar.setLayoutY(ay);
        reiniciar.setStyle ( "-fx-background-color: black" );
        reiniciar.setTextFill (Color.web ( "#fafbfd" ));



        Label resultadoA = new Label("Intentos:  |   Acertados");
        value.setLayoutX(lx); //1000
        value.setLayoutY(ly); //290
        value.setFont(Font.font(60));
        value.setStyle("-fx-text-fill: WHITE");

        acertado.setLayoutX(qx);
        acertado.setLayoutY(qy);
        acertado.setFont(Font.font(60));
        acertado.setStyle("-fx-text-fill: WHITE");

        resultadoA.setLayoutX(intentosx);
        resultadoA.setLayoutY(intentosy);
        resultadoA.setFont(Font.font(19));
        resultadoA.setStyle("-fx-text-fill: WHITE");
        modo = modoJuego;

        Label desafio = new Label();
        Label desafioValor = new Label();
        if(modoJuego.equals("duo")){
            desafio.setLayoutX(50); //1000
            desafio.setLayoutY(400); //290
            desafio.setFont(Font.font(90));
            desafio.setStyle("-fx-text-fill: WHITE");
            desafio.setText("¡ D E S A F I O ! :");

            desafioValor.setLayoutY(400);
            desafioValor.setLayoutX(800);
            desafioValor.setFont(Font.font(90));
            desafioValor.setStyle("-fx-text-fill: WHITE");
            valorDesafio=(int)(Math.random()*40);
            desafioValor.setText(String.valueOf(valorDesafio));

            desafioA.setLayoutX(1300);
            desafioA.setLayoutY(300);
            desafioA.setFont(Font.font(21));
            desafioA.setStyle ( "-fx-background-color: BLACK" );
            desafioA.setTextFill (Color.web ( "#fafbfd" ));



            if(valorDesafio<18 && valorDesafio>=10){
                desafioA.setText("D E S A  F I O: Encuentra dos pares " + "en menos de: " + valorDesafio + " intentos.");
            }
            else{
                if(valorDesafio>=18 && valorDesafio <24){
                    desafioA.setText("DESAFIO: Completa el juego en 18 intentos, ni una mas");
                }
                else {
                    if(valorDesafio>=24 && valorDesafio<30){
                        desafioA.setText("DESAFIO: encuentra 8 pares en menos de: " + valorDesafio/2 + " intentos");
                    }
                }
                if(valorDesafio>0 && valorDesafio <10){
                    desafioA.setText("DESAFIO: encuentra " + valorDesafio/2 + " pares en menos de " + valorDesafio + " intentos");
                }
                else{
                    if(valorDesafio>=30 && valorDesafio <=40){
                        desafioA.setText("DESAFIO: Completa el juego en " + valorDesafio +" intentos, ni una mas");
                    }
                    else {
                        if(valorDesafio==0){
                            desafioA.setText("DESAFIO EXTREMO: Completa el juego sin fallar ninguna, es decir, 18 intentos");
                        }
                    }
                }
            }
        }

        root.getChildren().addAll(rect, reiniciar,value,resultadoA,acertado,desafio,desafioValor,desafioA);



        char numeroValor = '1';
        List<numeros> numero = new ArrayList<>();
        for (int i = 0; i < NumeroA; i++) {
            numero.add(new numeros(String.valueOf(numeroValor)));
            numero.add(new numeros(String.valueOf(numeroValor)));
            numeroValor++;
        }

        Collections.shuffle(numero);

        for (int i = 0; i < numero.size(); i++) {
            numeros numeros = numero.get(i);
            numeros.setTranslateX(100 * (i % NumeroB));
            numeros.setTranslateY(100 * (i / NumeroB));
            numeros.setPrefHeight(120);
            numeros.setPrefWidth(120);
            root.getChildren().add(numeros);

        }

        return root;
    }

    private class numeros extends StackPane {
        private Text valor = new Text();

        public numeros(String value) {
            Rectangle rectangulo = new Rectangle(90, 90);
            rectangulo.setFill(null);


            valor.setStyle("-fx-text-fill: white");
            valor.setText(value);
            valor.setFont(Font.font(60));
            rectangulo.setFill(Color.WHITE);
            rectangulo.setStroke(Color.WHITE);



            setAlignment(Pos.CENTER);

            getChildren().addAll(rectangulo, valor);

            setOnMouseClicked(this::handleMouseClick);
            valorCerrado();
        }

        public void handleMouseClick(MouseEvent event) {
            if (clic1() || contadorClic == 0) {
                return;
            }

            contadorClic--;

            if (seleccion == null) {
                seleccion = this;
                valorAbierto(() -> {});
            }
            else {
                valorAbierto(() -> {
                    if (!evaluacionDatos(seleccion)) {
                        seleccion.valorCerrado();
                        this.valorCerrado();
                    }

                    seleccion = null;
                    contadorClic = 2;
                    value.setText(String.valueOf(contadorGeneral/2));
                    vect.setText(String.valueOf(contadorGeneral/2));
                });
            }

            contadorGeneral = contadorGeneral + 1;

        }

        public boolean clic1() {
            return valor.getOpacity() == 1;
        }

        public void valorAbierto(Runnable action) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), valor);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        public void valorCerrado() {
            FadeTransition ft = new FadeTransition(Duration.seconds(1.5), valor);
            ft.setToValue(0);
            ft.play();
        }

        public boolean evaluacionDatos(numeros valorEvaluado) {
            if(valor.getText().equals(valorEvaluado.valor.getText())){

                contadorAcertado = contadorAcertado + 1;
                acertado.setText(String.valueOf(contadorAcertado));
                act.setText(String.valueOf(contadorAcertado));

            }
            if(contadorAcertado==18 && modo.equals("dificil")){
                acertadoFinal();
            }
            else{
                if(contadorAcertado == 14 && modo.equals("medio")){
                    acertadoFinal();
                }
                else{
                    if(contadorAcertado == 8 && modo.equals("Facil")){
                        acertadoFinal();
                    }
                    else{
                        if(contadorAcertado==valorDesafio && modo.equals("duo")){

                                Pane root = new Pane();
                                root.setPrefSize(450, 450);

                                Stage stage = new Stage();
                                stage.setTitle("¡G A N A S T E !");
                                stage.setScene(new Scene(root, 450, 450));
                                stage.setResizable(false);
                                stage.getIcons().add(new Image("file:src/4.png"));

                                Rectangle rect = new Rectangle(0, 0, 470, 470);
                                Image bgImage = new Image("file:src/qRecurso 1.png");
                                rect.setFill(new ImagePattern(bgImage));

                                Label val = new Label("Haz ganado un bonus");
                                val.setLayoutX(35);
                                val.setLayoutY(175);
                                val.setFont(Font.font(40));
                                val.setStyle("-fx-text-fill: WHITE");


                                vect.setLayoutX(100);
                                vect.setLayoutY(220);
                                vect.setFont(Font.font(60));
                                vect.setStyle("-fx-text-fill: WHITE");


                                act.setLayoutX(320);
                                act.setLayoutY(220);
                                act.setFont(Font.font(60));
                                act.setStyle("-fx-text-fill: WHITE");

                                root.getChildren().addAll(rect,val,vect,act);
                                stage.show();
                                contadorGeneral= contadorGeneral-10;
                            //finJuego(memoV);
                        }
                    }
                }
            }

            System.out.println(contadorGeneral);
            if(contadorGeneral==valorDesafio && modo.equals("duo")){
                finJuego(memoV);
                Pane root = new Pane();
                root.setPrefSize(450, 450);

                Stage stage = new Stage();
                stage.setTitle(":( Objetivo perdido");
                stage.setScene(new Scene(root, 450, 450));
                stage.setResizable(false);
                stage.getIcons().add(new Image("file:src/4.png"));

                Rectangle rect = new Rectangle(0, 0, 470, 470);
                Image bgImage = new Image("file:src/qRecurso 1.png");
                rect.setFill(new ImagePattern(bgImage));

                Label val = new Label(":( Objetivo no cumplido");
                val.setLayoutX(35);
                val.setLayoutY(175);
                val.setFont(Font.font(40));
                val.setStyle("-fx-text-fill: WHITE");


                vect.setLayoutX(100);
                vect.setLayoutY(220);
                vect.setFont(Font.font(60));
                vect.setStyle("-fx-text-fill: WHITE");


                act.setLayoutX(320);
                act.setLayoutY(220);
                act.setFont(Font.font(60));
                act.setStyle("-fx-text-fill: WHITE");

                root.getChildren().addAll(rect,val,vect,act);
                stage.show();

                //finJuego(memoV);
            }

            return valor.getText().equals(valorEvaluado.valor.getText());

        }
    }


    public void acertadoFinal(){
        Pane root = new Pane();
        root.setPrefSize(450, 450);

        Stage stage = new Stage();
        stage.setTitle("¡GENIAL!, haz ganado");
        stage.setScene(new Scene(root, 450, 450));
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/4.png"));

        Rectangle rect = new Rectangle(0, 0, 470, 470);
        Image bgImage = new Image("file:src/qRecurso 1.png");
        rect.setFill(new ImagePattern(bgImage));

        Label val = new Label("Intentos  |   Acertados");
        val.setLayoutX(35);
        val.setLayoutY(175);
        val.setFont(Font.font(40));
        val.setStyle("-fx-text-fill: WHITE");


        vect.setLayoutX(100);
        vect.setLayoutY(220);
        vect.setFont(Font.font(60));
        vect.setStyle("-fx-text-fill: WHITE");


        act.setLayoutX(320);
        act.setLayoutY(220);
        act.setFont(Font.font(60));
        act.setStyle("-fx-text-fill: WHITE");

        root.getChildren().addAll(rect,val,vect,act);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}