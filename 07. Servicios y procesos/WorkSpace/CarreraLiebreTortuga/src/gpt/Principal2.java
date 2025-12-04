package gpt;

public class Principal2 {

    private static final int META = 70;

    private int posTortuga = 1;
    private int posLiebre = 1;

    private boolean turnoTortuga = false;
    private boolean turnoLiebre = false;
    private boolean carreraTerminada = false;

    // ------ Monitor para sincronización ------
    public synchronized void avanzarTortuga(int movimiento) {
        posTortuga += movimiento;
        if (posTortuga < 1) posTortuga = 1;
        if (posTortuga > META) posTortuga = META;

        turnoTortuga = true;
        notifyAll();

        while (!turnoLiebre && !carreraTerminada) {
            try { wait(); } catch (InterruptedException e) {}
        }
    }

    public synchronized void avanzarLiebre(int movimiento) {
        posLiebre += movimiento;
        if (posLiebre < 1) posLiebre = 1;
        if (posLiebre > META) posLiebre = META;

        turnoLiebre = true;
        notifyAll();

        while (!turnoTortuga && !carreraTerminada) {
            try { wait(); } catch (InterruptedException e) {}
        }
    }

    public synchronized void imprimirYComprobar() {
        if (!turnoTortuga || !turnoLiebre) return;

        imprimirLinea("T", posTortuga);
        imprimirLinea("L", posLiebre);

        // reset para siguiente segundo
        turnoTortuga = false;
        turnoLiebre = false;

        // comprobar meta
        if (posTortuga >= META && posLiebre >= META) {
            System.out.println("\n¡¡EMPATE!!");
            carreraTerminada = true;
        } else if (posTortuga >= META) {
            System.out.println("\n¡¡GANA LA TORTUGA!!");
            carreraTerminada = true;
        } else if (posLiebre >= META) {
            System.out.println("\n¡¡GANA LA LIEBRE!!");
            carreraTerminada = true;
        }

        notifyAll();
    }

    private void imprimirLinea(String animal, int pos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < pos; i++) sb.append(" ");
        sb.append(animal);
        System.out.println(sb.toString());
    }

    public boolean isCarreraTerminada() {
        return carreraTerminada;
    }

    // ----------------- HILOS --------------------
    class Tortuga extends Thread {
        @Override
        public void run() {
            while (!carreraTerminada) {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}

                int prob = (int)(Math.random()*100) + 1;
                int mov;

                if (prob <= 50) mov = 3;                // avance rápido
                else if (prob <= 70) mov = -6;          // resbalón
                else mov = 1;                           // avance lento

                avanzarTortuga(mov);
                imprimirYComprobar();
            }
        }
    }

    class Liebre extends Thread {
        @Override
        public void run() {
            while (!carreraTerminada) {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}

                int prob = (int)(Math.random()*100) + 1;
                int mov;

                if (prob <= 20) mov = 0;                // duerme
                else if (prob <= 40) mov = 9;           // gran salto
                else if (prob <= 50) mov = -12;         // resbalón grande
                else if (prob <= 80) mov = 1;           // pequeño salto
                else mov = -2;                          // pequeño resbalón

                avanzarLiebre(mov);
                imprimirYComprobar();
            }
        }
    }

    // -------- MAIN -----------------
    public static void main(String[] args) {
        Principal2 c = new Principal2();
        System.out.println("¡COMIENZA LA CARRERA!");

        Thread t = c.new Tortuga();
        Thread l = c.new Liebre();

        t.start();
        l.start();
    }
}

