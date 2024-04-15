
package cl.ucn.disc.poo.stdlib;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.event.KeyEvent;

/**
 * Programa qeu simula un juego donde
 * la pelotita negra es el jugador que tiene
 * que tiene que atrapar todas las manzanas
 * para ganar.
 *
 * @author Gimena Zavaleta-Loayza
 */
public class PuntosAlAzar {

    /*
     * The Main
     *
     * @param args to use
     */

    public static void main(String[] args) {


        // Rango de dibujo
        double min = -400;
        double max = 400;
        // Tamaño de la ventana
        StdDraw.setCanvasSize(400,400);
        // Definicion de la escala del lienzo de dibujo
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
        StdDraw.enableDoubleBuffering();
        // realizacion de 12 puntos (manzanas)
        int numeroDeManzanas = 12;
        int[] coordenadaManzanasX = new int[numeroDeManzanas];
        int[] coordenadaManzanasY = new int[numeroDeManzanas];
        crear_manzanas(min,max,coordenadaManzanasX,coordenadaManzanasY);
        // cordenada (jugador)
        double x1;
        double y1;
        x1 = (min+max)/2;
        y1 = (min+max)/2;

        //Velocidad del jugador
        int vjugador = 5;

        // Radio de la segunda pelota
        double radioJugador = 9;

        // Dibujar jugador
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(x1,y1,radioJugador);

        // Mostrar en pantalla
        StdDraw.show();

        // move
        boolean game = true;
        while(game)
        {

            // comprobar si se coliciono con una manzana
            colisionManzana(coordenadaManzanasX,coordenadaManzanasY,x1,y1);


            // comprobar si el jugador se movio
            if(StdDraw.isKeyPressed(KeyEvent.VK_W))
            {
                StdDraw.clear();
                System.out.println("up");
                y1++;
                reDibujarManzanas(min,max,coordenadaManzanasX,coordenadaManzanasY);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledCircle(x1,y1,radioJugador);
                StdDraw.show();
                StdDraw.pause(vjugador);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E))
            {
                StdDraw.clear();
                System.out.println("right");
                x1++;
                reDibujarManzanas(min,max,coordenadaManzanasX,coordenadaManzanasY);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledCircle(x1,y1,radioJugador);
                StdDraw.show();
                StdDraw.pause(vjugador);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_D ))
            {
                StdDraw.clear();
                System.out.println("left");
                x1--;
                reDibujarManzanas(min,max,coordenadaManzanasX,coordenadaManzanasY);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledCircle(x1,y1,radioJugador);
                StdDraw.show();
                StdDraw.pause(vjugador);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_S ))
            {
                StdDraw.clear();
                System.out.println("down");
                y1--;
                reDibujarManzanas(min,max,coordenadaManzanasX,coordenadaManzanasY);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledCircle(x1,y1,radioJugador);
                StdDraw.show();
                StdDraw.pause(vjugador);
            }
            // Contador de manzanas comidas
            int manzanasComidas = contadorManzanasComidas(coordenadaManzanasX, coordenadaManzanasY);

            // Mostrar contador de manzanas comidas
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.textLeft( min +20, min +20, String.valueOf(manzanasComidas));

            // Mostrar en pantalla
            StdDraw.show();

            // comprobar de que existan manzanas para colisionar y terminar juego
            if (!finalizarJuego(coordenadaManzanasX,coordenadaManzanasY))
            {
                game = false;

                // Mostrar mensaje de victoria
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(0, 0, "JUEGO FINALIZADO, HAS GANADO");
                StdDraw.show();
                break;
            }

        }

    }

    private static void crear_manzanas( double min, double max, int[] coordenadaManzanasX, int[] coordenadaManzanasY) {

        int numeroDeManzanas = 12;

        double x0 = 0;
        double y0 = 0;
        int radioManzana = 0;
        for (int i=0; i < numeroDeManzanas ; i++){
            // Cordenadas de las manzanas
            x0 = min + (max-min)* Math.random();
            y0 = min + (max-min)* Math.random();
            // Radio de las manzanas
            radioManzana= 9;
            // Dibujo de las manzanas
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x0,y0,radioManzana);
            // Guardar(asignar) coordenadas de las manzanas
            coordenadaManzanasX[i] = (int)x0;
            coordenadaManzanasY[i] = (int)y0;
        }
    }

    private static void reDibujarManzanas( double min, double max, int[] coordenadaManzanasX, int[] coordenadaManzanasY) {

        int numeroDeManzanas = 12;

        double x0 = 0;
        double y0 = 0;
        int radioManzana = 0;
        for (int i=0; i < numeroDeManzanas ; i++){
            // Cordenadas de las manzanas
            x0 = coordenadaManzanasX[i];
            y0 = coordenadaManzanasY[i];
            // Radio de las manzanas
            radioManzana= 9;
            // Dibujo de las manzanas
            if(x0 == 0 && y0 == 0)
            {
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.filledCircle(x0,y0,radioManzana);
            }else{
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(x0,y0,radioManzana);
            }

        }
    }

    // Coliciones
    // Variables de las cordenadas del jugador y de las manzanas
    private static void colisionManzana(int[] coordenadaManzanasX, int[] coordenadaManzanasY, double x1,double y1)
    {
        for (int i = 0; i < coordenadaManzanasX.length; i++)
        {
            // Cuando las manzanas y el jugador se encuentran mediante sus cordenadas
            if ((coordenadaManzanasX[i] < (int)x1+9 && coordenadaManzanasX[i] >(int)x1-9) && (coordenadaManzanasY[i] < (int)y1+9 && coordenadaManzanasY[i] > (int)y1-9)){
                  // eliminar mamzanas
                  coordenadaManzanasX[i]=0;
                  coordenadaManzanasY[i]=0;
            }
        }
    }
    //Contador de manzanas
    private static int contadorManzanasComidas(int[] coordenadaManzanasX, int[] coordenadaManzanasY) {
        int contador = 0;
        // Recorre 12 veces por las 12 manzanas
        for (int i = 0; i < coordenadaManzanasX.length; i++) {
            // Busca por manzanas mediante sus cordenas igual a 0 para que se sume 1
            if (coordenadaManzanasX[i] == 0 && coordenadaManzanasY[i] == 0) {
                contador++;
            }
        }
        return contador;
    }

    // finalizacion del juego
    private static boolean finalizarJuego( int[] coordenadaManzanasX, int[] coordenadaManzanasY)
    {
        boolean existenManzanas = true;
        int manzanasColisionadas = 0;
        // Recorre las 12 manzanas del juego y verifica sus cordenadas y se le va sumando las manzanas colisionadas
        for (int i = 0; i < coordenadaManzanasX.length; i++)
        {
            if (coordenadaManzanasX[i] == 0 && coordenadaManzanasY[i] == 0)
            {
               manzanasColisionadas++;
            }
        }

        if(manzanasColisionadas == 12)
        {
            existenManzanas = false;
            // aqui debes entregar el mensaje al jugador
        }

        return existenManzanas;
    }
}
