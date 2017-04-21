
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;


/**
 *
 * @author David
 */
public class ServerBoardJFrame extends javax.swing.JFrame 
{
    private JButton[][] leftSeaButtonArray;
    private JButton[][] rightSeaButtonArray;
    static String target;
    /**
     * Creates new form BoardJFrame
     */
    class GameServer extends Thread{
        private ServerSocket tempSocket;
        private DataInputStream in;
        private DataOutputStream out;
        public GameServer(ServerSocket tempSocket) throws IOException{
            this.tempSocket = tempSocket;
        }
        
        public void run() {
            Socket socket = new Socket();
            try {
                socket = tempSocket.accept();
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(ServerBoardJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            ActionListener firingAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        out.writeUTF(target);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerBoardJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            jTextField1.setText("Connected!");
            char[] hitMarker = new char[2];
            int letter = 0;
            int number = 0;
            while (socket.isConnected()) {
                try {
                    hitMarker = in.readUTF().toCharArray();
                } catch (IOException ex) {
                    Logger.getLogger(ServerBoardJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                switch (hitMarker[0]) {
                    case 'A':
                        letter = 0;
                        break;
                    case 'B':
                        letter = 1;
                        break;
                    case 'C':
                        letter = 2;
                        break;
                    case 'D':
                        letter = 3;
                        break;
                    case 'E':
                        letter = 4;
                        break;
                    case 'F':
                        letter = 5;
                        break;
                    case 'G':
                        letter = 6;
                        break;
                    case 'H':
                        letter = 7;
                        break;
                    case 'I':
                        letter = 8;
                        break;
                    case 'J':
                        letter = 9;
                        break;
                }
                number = Integer.parseInt("" + hitMarker[1]);
                rightSeaButtonArray[letter][number - 1].setEnabled(false);
            }
        }
    }
    
    public static void main(String args[]) throws IOException 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ServerBoardJFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ServerBoardJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public ServerBoardJFrame() throws IOException {
        initComponents();
        this.rightSeaButtonArray = new JButton[][]{
            {A1, A2, A3, A4, A5, A6, A7, A8, A9, A10}, 
            {B1, B2, B3, B4, B5, B6, B7, B8, B9, B10},
            {C1, C2, C3, C4, C5, C6, C7, C8, C9, C10},
            {D1, D2, D3, D4, D5, D6, D7, D8, D9, D10},
            {E1, E2, E3, E4, E5, E6, E7, E8, E9, E10},
            {F1, F2, F3, F4, F5, F6, F7, F8, F9, F10},
            {G1, G2, G3, G4, G5, G6, G7, G8, G9, G10},
            {H1, H2, H3, H4, H5, H6, H7, H8, H9, H10},
            {I1, I2, I3, I4, I5, I6, I7, I8, I9, I10},
            {J1, J2, J3, J4, J5, J6, J7, J8, J9, J10}
        };
        this.leftSeaButtonArray = new JButton[][]{
            {A11, A12, A13, A14, A15, A16, A17, A18, A19, A20}, 
            {B11, B12, B13, B14, B15, B16, B17, B18, B19, B20},
            {C11, C12, C13, C14, C15, C16, C17, C18, C19, C20},
            {D11, D12, D13, D14, D15, D16, D17, D18, D19, D20},
            {E11, E12, E13, E14, E15, E16, E17, E18, E19, E20},
            {F12, F12, F13, F14, F15, F16, F17, F18, F19, F20},
            {G11, G12, G13, G14, G15, G16, G17, G18, G19, G20},
            {H11, H12, H13, H14, H15, H16, H17, H18, H19, H20},
            {I11, I12, I13, I14, I15, I16, I17, I18, I19, I20},
            {J11, J12, J13, J14, J15, J16, J17, J18, J19, J20}
        };
        ActionListener leftSeaButtonAction = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                target = e.getActionCommand();
            }
        };
        for(JButton[] row : leftSeaButtonArray){
            for(JButton item : row){
                item.addActionListener(leftSeaButtonAction);
            }
        }
        ServerSocket tempSocket = new ServerSocket(9090);
        GameServer gs = new GameServer(tempSocket);
        gs.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LeftSea = new javax.swing.JPanel();
        E = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        A1 = new javax.swing.JButton();
        A2 = new javax.swing.JButton();
        A3 = new javax.swing.JButton();
        A4 = new javax.swing.JButton();
        A5 = new javax.swing.JButton();
        A6 = new javax.swing.JButton();
        A7 = new javax.swing.JButton();
        A8 = new javax.swing.JButton();
        A9 = new javax.swing.JButton();
        A10 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        B1 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        B9 = new javax.swing.JButton();
        B10 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        C1 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        C6 = new javax.swing.JButton();
        C7 = new javax.swing.JButton();
        C8 = new javax.swing.JButton();
        C9 = new javax.swing.JButton();
        C10 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        D1 = new javax.swing.JButton();
        D2 = new javax.swing.JButton();
        D3 = new javax.swing.JButton();
        D4 = new javax.swing.JButton();
        D5 = new javax.swing.JButton();
        D6 = new javax.swing.JButton();
        D7 = new javax.swing.JButton();
        D8 = new javax.swing.JButton();
        D9 = new javax.swing.JButton();
        D10 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        E1 = new javax.swing.JButton();
        E2 = new javax.swing.JButton();
        E3 = new javax.swing.JButton();
        E4 = new javax.swing.JButton();
        E5 = new javax.swing.JButton();
        E6 = new javax.swing.JButton();
        E7 = new javax.swing.JButton();
        E8 = new javax.swing.JButton();
        E9 = new javax.swing.JButton();
        E10 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        F1 = new javax.swing.JButton();
        F2 = new javax.swing.JButton();
        F3 = new javax.swing.JButton();
        F4 = new javax.swing.JButton();
        F5 = new javax.swing.JButton();
        F6 = new javax.swing.JButton();
        F7 = new javax.swing.JButton();
        F8 = new javax.swing.JButton();
        F9 = new javax.swing.JButton();
        F10 = new javax.swing.JButton();
        rowG = new javax.swing.JLabel();
        G1 = new javax.swing.JButton();
        G2 = new javax.swing.JButton();
        G3 = new javax.swing.JButton();
        G4 = new javax.swing.JButton();
        G5 = new javax.swing.JButton();
        G6 = new javax.swing.JButton();
        G7 = new javax.swing.JButton();
        G8 = new javax.swing.JButton();
        G9 = new javax.swing.JButton();
        G10 = new javax.swing.JButton();
        rowH = new javax.swing.JLabel();
        H1 = new javax.swing.JButton();
        H2 = new javax.swing.JButton();
        H3 = new javax.swing.JButton();
        H4 = new javax.swing.JButton();
        H5 = new javax.swing.JButton();
        H6 = new javax.swing.JButton();
        H7 = new javax.swing.JButton();
        H8 = new javax.swing.JButton();
        H9 = new javax.swing.JButton();
        H10 = new javax.swing.JButton();
        rowI = new javax.swing.JLabel();
        I1 = new javax.swing.JButton();
        I2 = new javax.swing.JButton();
        I3 = new javax.swing.JButton();
        I4 = new javax.swing.JButton();
        I5 = new javax.swing.JButton();
        I6 = new javax.swing.JButton();
        I7 = new javax.swing.JButton();
        I8 = new javax.swing.JButton();
        I9 = new javax.swing.JButton();
        I10 = new javax.swing.JButton();
        rowJ = new javax.swing.JLabel();
        J1 = new javax.swing.JButton();
        J2 = new javax.swing.JButton();
        J3 = new javax.swing.JButton();
        J4 = new javax.swing.JButton();
        J5 = new javax.swing.JButton();
        J6 = new javax.swing.JButton();
        J7 = new javax.swing.JButton();
        J8 = new javax.swing.JButton();
        J9 = new javax.swing.JButton();
        J10 = new javax.swing.JButton();
        FIRE = new javax.swing.JButton();
        READY = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        J = new javax.swing.JButton();
        RightSea = new javax.swing.JPanel();
        blankCorner1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        A11 = new javax.swing.JButton();
        A12 = new javax.swing.JButton();
        A13 = new javax.swing.JButton();
        A14 = new javax.swing.JButton();
        A15 = new javax.swing.JButton();
        A16 = new javax.swing.JButton();
        A17 = new javax.swing.JButton();
        A18 = new javax.swing.JButton();
        A19 = new javax.swing.JButton();
        A20 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        B11 = new javax.swing.JButton();
        B12 = new javax.swing.JButton();
        B13 = new javax.swing.JButton();
        B14 = new javax.swing.JButton();
        B15 = new javax.swing.JButton();
        B16 = new javax.swing.JButton();
        B17 = new javax.swing.JButton();
        B18 = new javax.swing.JButton();
        B19 = new javax.swing.JButton();
        B20 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        C11 = new javax.swing.JButton();
        C12 = new javax.swing.JButton();
        C13 = new javax.swing.JButton();
        C14 = new javax.swing.JButton();
        C15 = new javax.swing.JButton();
        C16 = new javax.swing.JButton();
        C17 = new javax.swing.JButton();
        C18 = new javax.swing.JButton();
        C19 = new javax.swing.JButton();
        C20 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        D11 = new javax.swing.JButton();
        D12 = new javax.swing.JButton();
        D13 = new javax.swing.JButton();
        D14 = new javax.swing.JButton();
        D15 = new javax.swing.JButton();
        D16 = new javax.swing.JButton();
        D17 = new javax.swing.JButton();
        D18 = new javax.swing.JButton();
        D19 = new javax.swing.JButton();
        D20 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        E11 = new javax.swing.JButton();
        E12 = new javax.swing.JButton();
        E13 = new javax.swing.JButton();
        E14 = new javax.swing.JButton();
        E15 = new javax.swing.JButton();
        E16 = new javax.swing.JButton();
        E17 = new javax.swing.JButton();
        E18 = new javax.swing.JButton();
        E19 = new javax.swing.JButton();
        E20 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        F11 = new javax.swing.JButton();
        F12 = new javax.swing.JButton();
        F13 = new javax.swing.JButton();
        F14 = new javax.swing.JButton();
        F15 = new javax.swing.JButton();
        F16 = new javax.swing.JButton();
        F17 = new javax.swing.JButton();
        F18 = new javax.swing.JButton();
        F19 = new javax.swing.JButton();
        F20 = new javax.swing.JButton();
        rowG1 = new javax.swing.JLabel();
        G11 = new javax.swing.JButton();
        G12 = new javax.swing.JButton();
        G13 = new javax.swing.JButton();
        G14 = new javax.swing.JButton();
        G15 = new javax.swing.JButton();
        G16 = new javax.swing.JButton();
        G17 = new javax.swing.JButton();
        G18 = new javax.swing.JButton();
        G19 = new javax.swing.JButton();
        G20 = new javax.swing.JButton();
        rowH1 = new javax.swing.JLabel();
        H11 = new javax.swing.JButton();
        H12 = new javax.swing.JButton();
        H13 = new javax.swing.JButton();
        H14 = new javax.swing.JButton();
        H15 = new javax.swing.JButton();
        H16 = new javax.swing.JButton();
        H17 = new javax.swing.JButton();
        H18 = new javax.swing.JButton();
        H19 = new javax.swing.JButton();
        H20 = new javax.swing.JButton();
        rowI1 = new javax.swing.JLabel();
        I11 = new javax.swing.JButton();
        I12 = new javax.swing.JButton();
        I13 = new javax.swing.JButton();
        I14 = new javax.swing.JButton();
        I15 = new javax.swing.JButton();
        I16 = new javax.swing.JButton();
        I17 = new javax.swing.JButton();
        I18 = new javax.swing.JButton();
        I19 = new javax.swing.JButton();
        I20 = new javax.swing.JButton();
        rowJ1 = new javax.swing.JLabel();
        J11 = new javax.swing.JButton();
        J12 = new javax.swing.JButton();
        J13 = new javax.swing.JButton();
        J14 = new javax.swing.JButton();
        J15 = new javax.swing.JButton();
        J16 = new javax.swing.JButton();
        J17 = new javax.swing.JButton();
        J18 = new javax.swing.JButton();
        J19 = new javax.swing.JButton();
        J20 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battleship Board Server");

        LeftSea.setBackground(new java.awt.Color(47, 174, 238));
        LeftSea.setMaximumSize(new java.awt.Dimension(34, 34));
        LeftSea.setMinimumSize(new java.awt.Dimension(34, 34));
        LeftSea.setName("Board Status");
        LeftSea.setPreferredSize(new java.awt.Dimension(23, 23));
        LeftSea.setLayout(new java.awt.GridLayout(11, 11, 1, 1));
        LeftSea.add(E);

        jLabel8.setText("   1");
        LeftSea.add(jLabel8);

        jLabel15.setText("   2");
        LeftSea.add(jLabel15);

        jLabel3.setText("   3");
        LeftSea.add(jLabel3);

        jLabel7.setText("   4");
        LeftSea.add(jLabel7);

        jLabel9.setText("    5");
        LeftSea.add(jLabel9);

        jLabel11.setText("    6");
        LeftSea.add(jLabel11);

        jLabel12.setText("    7");
        LeftSea.add(jLabel12);

        jLabel13.setText("    8");
        LeftSea.add(jLabel13);

        jLabel1.setText("    9");
        LeftSea.add(jLabel1);

        jLabel19.setText("   10");
        LeftSea.add(jLabel19);

        jLabel17.setText("    A");
        LeftSea.add(jLabel17);
        LeftSea.add(A1);
        LeftSea.add(A2);
        LeftSea.add(A3);
        LeftSea.add(A4);
        LeftSea.add(A5);
        LeftSea.add(A6);
        LeftSea.add(A7);
        LeftSea.add(A8);
        LeftSea.add(A9);
        LeftSea.add(A10);

        jLabel16.setText("    B");
        LeftSea.add(jLabel16);

        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });
        LeftSea.add(B1);
        LeftSea.add(B2);
        LeftSea.add(B3);
        LeftSea.add(B4);
        LeftSea.add(B5);
        LeftSea.add(B6);
        LeftSea.add(B7);

        B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B8ActionPerformed(evt);
            }
        });
        LeftSea.add(B8);
        LeftSea.add(B9);
        LeftSea.add(B10);

        jLabel10.setText("    C");
        LeftSea.add(jLabel10);
        LeftSea.add(C1);
        LeftSea.add(C2);
        LeftSea.add(C3);
        LeftSea.add(C4);
        LeftSea.add(C5);
        LeftSea.add(C6);
        LeftSea.add(C7);
        LeftSea.add(C8);
        LeftSea.add(C9);
        LeftSea.add(C10);

        jLabel4.setText("    D");
        LeftSea.add(jLabel4);
        LeftSea.add(D1);
        LeftSea.add(D2);
        LeftSea.add(D3);
        LeftSea.add(D4);
        LeftSea.add(D5);
        LeftSea.add(D6);
        LeftSea.add(D7);
        LeftSea.add(D8);
        LeftSea.add(D9);
        LeftSea.add(D10);

        jLabel18.setText("    E");
        LeftSea.add(jLabel18);
        LeftSea.add(E1);
        LeftSea.add(E2);
        LeftSea.add(E3);
        LeftSea.add(E4);
        LeftSea.add(E5);
        LeftSea.add(E6);
        LeftSea.add(E7);
        LeftSea.add(E8);
        LeftSea.add(E9);
        LeftSea.add(E10);

        jLabel5.setText("    F");
        LeftSea.add(jLabel5);
        LeftSea.add(F1);
        LeftSea.add(F2);
        LeftSea.add(F3);
        LeftSea.add(F4);
        LeftSea.add(F5);
        LeftSea.add(F6);
        LeftSea.add(F7);
        LeftSea.add(F8);
        LeftSea.add(F9);
        LeftSea.add(F10);

        rowG.setText("    G");
        LeftSea.add(rowG);
        LeftSea.add(G1);
        LeftSea.add(G2);
        LeftSea.add(G3);
        LeftSea.add(G4);
        LeftSea.add(G5);
        LeftSea.add(G6);
        LeftSea.add(G7);
        LeftSea.add(G8);
        LeftSea.add(G9);
        LeftSea.add(G10);

        rowH.setText("    H");
        LeftSea.add(rowH);
        LeftSea.add(H1);
        LeftSea.add(H2);
        LeftSea.add(H3);
        LeftSea.add(H4);
        LeftSea.add(H5);
        LeftSea.add(H6);
        LeftSea.add(H7);
        LeftSea.add(H8);
        LeftSea.add(H9);
        LeftSea.add(H10);

        rowI.setText("    I");
        LeftSea.add(rowI);
        LeftSea.add(I1);
        LeftSea.add(I2);
        LeftSea.add(I3);
        LeftSea.add(I4);
        LeftSea.add(I5);
        LeftSea.add(I6);
        LeftSea.add(I7);
        LeftSea.add(I8);
        LeftSea.add(I9);
        LeftSea.add(I10);

        rowJ.setText("    J");
        LeftSea.add(rowJ);
        LeftSea.add(J1);
        LeftSea.add(J2);
        LeftSea.add(J3);
        LeftSea.add(J4);
        LeftSea.add(J5);
        LeftSea.add(J6);
        LeftSea.add(J7);
        LeftSea.add(J8);
        LeftSea.add(J9);
        LeftSea.add(J10);

        FIRE.setText("FIRE!");

        READY.setText("READY");

        jButton1.setText("a1");

        jButton2.setText("jButton1");

        jButton3.setText("jButton1");

        RightSea.setBackground(new java.awt.Color(47, 174, 238));
        RightSea.setMaximumSize(new java.awt.Dimension(34, 34));
        RightSea.setMinimumSize(new java.awt.Dimension(34, 34));
        RightSea.setName("Board Status");
        RightSea.setPreferredSize(new java.awt.Dimension(23, 23));
        RightSea.setLayout(new java.awt.GridLayout(11, 11, 1, 1));
        RightSea.add(blankCorner1);

        jLabel14.setText("   1");
        RightSea.add(jLabel14);

        jLabel20.setText("   2");
        RightSea.add(jLabel20);

        jLabel6.setText("   3");
        RightSea.add(jLabel6);

        jLabel21.setText("   4");
        RightSea.add(jLabel21);

        jLabel22.setText("    5");
        RightSea.add(jLabel22);

        jLabel23.setText("    6");
        RightSea.add(jLabel23);

        jLabel24.setText("    7");
        RightSea.add(jLabel24);

        jLabel25.setText("    8");
        RightSea.add(jLabel25);

        jLabel2.setText("    9");
        RightSea.add(jLabel2);

        jLabel26.setText("   10");
        RightSea.add(jLabel26);

        jLabel27.setText("    A");
        RightSea.add(jLabel27);
        RightSea.add(A11);
        RightSea.add(A12);
        RightSea.add(A13);
        RightSea.add(A14);
        RightSea.add(A15);
        RightSea.add(A16);
        RightSea.add(A17);
        RightSea.add(A18);
        RightSea.add(A19);
        RightSea.add(A20);

        jLabel28.setText("    B");
        RightSea.add(jLabel28);

        B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B11ActionPerformed(evt);
            }
        });
        RightSea.add(B11);
        RightSea.add(B12);
        RightSea.add(B13);
        RightSea.add(B14);
        RightSea.add(B15);
        RightSea.add(B16);
        RightSea.add(B17);

        B18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B18ActionPerformed(evt);
            }
        });
        RightSea.add(B18);
        RightSea.add(B19);
        RightSea.add(B20);

        jLabel29.setText("    C");
        RightSea.add(jLabel29);
        RightSea.add(C11);
        RightSea.add(C12);
        RightSea.add(C13);
        RightSea.add(C14);
        RightSea.add(C15);
        RightSea.add(C16);
        RightSea.add(C17);
        RightSea.add(C18);

        C19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C19ActionPerformed(evt);
            }
        });
        RightSea.add(C19);
        RightSea.add(C20);

        jLabel30.setText("    D");
        RightSea.add(jLabel30);
        RightSea.add(D11);

        D12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D12ActionPerformed(evt);
            }
        });
        RightSea.add(D12);
        RightSea.add(D13);
        RightSea.add(D14);
        RightSea.add(D15);
        RightSea.add(D16);
        RightSea.add(D17);
        RightSea.add(D18);
        RightSea.add(D19);
        RightSea.add(D20);

        jLabel31.setText("    E");
        RightSea.add(jLabel31);
        RightSea.add(E11);
        RightSea.add(E12);
        RightSea.add(E13);
        RightSea.add(E14);
        RightSea.add(E15);
        RightSea.add(E16);
        RightSea.add(E17);
        RightSea.add(E18);
        RightSea.add(E19);
        RightSea.add(E20);

        jLabel32.setText("    F");
        RightSea.add(jLabel32);

        F11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F11ActionPerformed(evt);
            }
        });
        RightSea.add(F11);
        RightSea.add(F12);
        RightSea.add(F13);
        RightSea.add(F14);
        RightSea.add(F15);
        RightSea.add(F16);
        RightSea.add(F17);
        RightSea.add(F18);
        RightSea.add(F19);
        RightSea.add(F20);

        rowG1.setText("    G");
        RightSea.add(rowG1);
        RightSea.add(G11);
        RightSea.add(G12);
        RightSea.add(G13);
        RightSea.add(G14);
        RightSea.add(G15);
        RightSea.add(G16);
        RightSea.add(G17);
        RightSea.add(G18);
        RightSea.add(G19);
        RightSea.add(G20);

        rowH1.setText("    H");
        RightSea.add(rowH1);
        RightSea.add(H11);
        RightSea.add(H12);
        RightSea.add(H13);
        RightSea.add(H14);
        RightSea.add(H15);
        RightSea.add(H16);
        RightSea.add(H17);
        RightSea.add(H18);
        RightSea.add(H19);
        RightSea.add(H20);

        rowI1.setText("    I");
        RightSea.add(rowI1);
        RightSea.add(I11);
        RightSea.add(I12);
        RightSea.add(I13);
        RightSea.add(I14);
        RightSea.add(I15);
        RightSea.add(I16);
        RightSea.add(I17);
        RightSea.add(I18);
        RightSea.add(I19);
        RightSea.add(I20);

        rowJ1.setText("    J");
        RightSea.add(rowJ1);
        RightSea.add(J11);
        RightSea.add(J12);
        RightSea.add(J13);
        RightSea.add(J14);
        RightSea.add(J15);
        RightSea.add(J16);
        RightSea.add(J17);
        RightSea.add(J18);
        RightSea.add(J19);
        RightSea.add(J20);

        jButton4.setText("jButton4");

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(FIRE)
                                .addGap(30, 30, 30)
                                .addComponent(J)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(READY))
                            .addComponent(RightSea, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(399, 399, 399)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4)
                                    .addComponent(LeftSea, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(LeftSea, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(RightSea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FIRE)
                        .addComponent(READY)
                        .addComponent(jButton4))
                    .addComponent(J))
                .addGap(39, 39, 39)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B1ActionPerformed

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B8ActionPerformed
        // TODO addling code here:
    }//GEN-LAST:event_B8ActionPerformed

    private void B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B11ActionPerformed

    private void B18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B18ActionPerformed

    private void F11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F11ActionPerformed

    private void C19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C19ActionPerformed

    private void D12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_D12ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A1;
    private javax.swing.JButton A10;
    private javax.swing.JButton A11;
    private javax.swing.JButton A12;
    private javax.swing.JButton A13;
    private javax.swing.JButton A14;
    private javax.swing.JButton A15;
    private javax.swing.JButton A16;
    private javax.swing.JButton A17;
    private javax.swing.JButton A18;
    private javax.swing.JButton A19;
    private javax.swing.JButton A2;
    private javax.swing.JButton A20;
    private javax.swing.JButton A3;
    private javax.swing.JButton A4;
    private javax.swing.JButton A5;
    private javax.swing.JButton A6;
    private javax.swing.JButton A7;
    private javax.swing.JButton A8;
    private javax.swing.JButton A9;
    private javax.swing.JButton B1;
    private javax.swing.JButton B10;
    private javax.swing.JButton B11;
    private javax.swing.JButton B12;
    private javax.swing.JButton B13;
    private javax.swing.JButton B14;
    private javax.swing.JButton B15;
    private javax.swing.JButton B16;
    private javax.swing.JButton B17;
    private javax.swing.JButton B18;
    private javax.swing.JButton B19;
    private javax.swing.JButton B2;
    private javax.swing.JButton B20;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton B9;
    private javax.swing.JButton C1;
    private javax.swing.JButton C10;
    private javax.swing.JButton C11;
    private javax.swing.JButton C12;
    private javax.swing.JButton C13;
    private javax.swing.JButton C14;
    private javax.swing.JButton C15;
    private javax.swing.JButton C16;
    private javax.swing.JButton C17;
    private javax.swing.JButton C18;
    private javax.swing.JButton C19;
    private javax.swing.JButton C2;
    private javax.swing.JButton C20;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    private javax.swing.JButton C8;
    private javax.swing.JButton C9;
    private javax.swing.JButton D1;
    private javax.swing.JButton D10;
    private javax.swing.JButton D11;
    private javax.swing.JButton D12;
    private javax.swing.JButton D13;
    private javax.swing.JButton D14;
    private javax.swing.JButton D15;
    private javax.swing.JButton D16;
    private javax.swing.JButton D17;
    private javax.swing.JButton D18;
    private javax.swing.JButton D19;
    private javax.swing.JButton D2;
    private javax.swing.JButton D20;
    private javax.swing.JButton D3;
    private javax.swing.JButton D4;
    private javax.swing.JButton D5;
    private javax.swing.JButton D6;
    private javax.swing.JButton D7;
    private javax.swing.JButton D8;
    private javax.swing.JButton D9;
    private javax.swing.JLabel E;
    private javax.swing.JButton E1;
    private javax.swing.JButton E10;
    private javax.swing.JButton E11;
    private javax.swing.JButton E12;
    private javax.swing.JButton E13;
    private javax.swing.JButton E14;
    private javax.swing.JButton E15;
    private javax.swing.JButton E16;
    private javax.swing.JButton E17;
    private javax.swing.JButton E18;
    private javax.swing.JButton E19;
    private javax.swing.JButton E2;
    private javax.swing.JButton E20;
    private javax.swing.JButton E3;
    private javax.swing.JButton E4;
    private javax.swing.JButton E5;
    private javax.swing.JButton E6;
    private javax.swing.JButton E7;
    private javax.swing.JButton E8;
    private javax.swing.JButton E9;
    private javax.swing.JButton F1;
    private javax.swing.JButton F10;
    private javax.swing.JButton F11;
    private javax.swing.JButton F12;
    private javax.swing.JButton F13;
    private javax.swing.JButton F14;
    private javax.swing.JButton F15;
    private javax.swing.JButton F16;
    private javax.swing.JButton F17;
    private javax.swing.JButton F18;
    private javax.swing.JButton F19;
    private javax.swing.JButton F2;
    private javax.swing.JButton F20;
    private javax.swing.JButton F3;
    private javax.swing.JButton F4;
    private javax.swing.JButton F5;
    private javax.swing.JButton F6;
    private javax.swing.JButton F7;
    private javax.swing.JButton F8;
    private javax.swing.JButton F9;
    private javax.swing.JButton FIRE;
    private javax.swing.JButton G1;
    private javax.swing.JButton G10;
    private javax.swing.JButton G11;
    private javax.swing.JButton G12;
    private javax.swing.JButton G13;
    private javax.swing.JButton G14;
    private javax.swing.JButton G15;
    private javax.swing.JButton G16;
    private javax.swing.JButton G17;
    private javax.swing.JButton G18;
    private javax.swing.JButton G19;
    private javax.swing.JButton G2;
    private javax.swing.JButton G20;
    private javax.swing.JButton G3;
    private javax.swing.JButton G4;
    private javax.swing.JButton G5;
    private javax.swing.JButton G6;
    private javax.swing.JButton G7;
    private javax.swing.JButton G8;
    private javax.swing.JButton G9;
    private javax.swing.JButton H1;
    private javax.swing.JButton H10;
    private javax.swing.JButton H11;
    private javax.swing.JButton H12;
    private javax.swing.JButton H13;
    private javax.swing.JButton H14;
    private javax.swing.JButton H15;
    private javax.swing.JButton H16;
    private javax.swing.JButton H17;
    private javax.swing.JButton H18;
    private javax.swing.JButton H19;
    private javax.swing.JButton H2;
    private javax.swing.JButton H20;
    private javax.swing.JButton H3;
    private javax.swing.JButton H4;
    private javax.swing.JButton H5;
    private javax.swing.JButton H6;
    private javax.swing.JButton H7;
    private javax.swing.JButton H8;
    private javax.swing.JButton H9;
    private javax.swing.JButton I1;
    private javax.swing.JButton I10;
    private javax.swing.JButton I11;
    private javax.swing.JButton I12;
    private javax.swing.JButton I13;
    private javax.swing.JButton I14;
    private javax.swing.JButton I15;
    private javax.swing.JButton I16;
    private javax.swing.JButton I17;
    private javax.swing.JButton I18;
    private javax.swing.JButton I19;
    private javax.swing.JButton I2;
    private javax.swing.JButton I20;
    private javax.swing.JButton I3;
    private javax.swing.JButton I4;
    private javax.swing.JButton I5;
    private javax.swing.JButton I6;
    private javax.swing.JButton I7;
    private javax.swing.JButton I8;
    private javax.swing.JButton I9;
    private javax.swing.JButton J;
    private javax.swing.JButton J1;
    private javax.swing.JButton J10;
    private javax.swing.JButton J11;
    private javax.swing.JButton J12;
    private javax.swing.JButton J13;
    private javax.swing.JButton J14;
    private javax.swing.JButton J15;
    private javax.swing.JButton J16;
    private javax.swing.JButton J17;
    private javax.swing.JButton J18;
    private javax.swing.JButton J19;
    private javax.swing.JButton J2;
    private javax.swing.JButton J20;
    private javax.swing.JButton J3;
    private javax.swing.JButton J4;
    private javax.swing.JButton J5;
    private javax.swing.JButton J6;
    private javax.swing.JButton J7;
    private javax.swing.JButton J8;
    private javax.swing.JButton J9;
    private javax.swing.JPanel LeftSea;
    private javax.swing.JButton READY;
    private javax.swing.JPanel RightSea;
    private javax.swing.JLabel blankCorner1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel rowG;
    private javax.swing.JLabel rowG1;
    private javax.swing.JLabel rowH;
    private javax.swing.JLabel rowH1;
    private javax.swing.JLabel rowI;
    private javax.swing.JLabel rowI1;
    private javax.swing.JLabel rowJ;
    private javax.swing.JLabel rowJ1;
    // End of variables declaration//GEN-END:variables
}
