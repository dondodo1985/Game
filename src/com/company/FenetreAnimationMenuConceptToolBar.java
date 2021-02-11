package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
//Les imports habituels
import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
//import ToolBar
import javax.swing.JToolBar;

//
public class FenetreAnimationMenuConceptToolBar extends JFrame {
    private PanneauMorphingMenu pan = new PanneauMorphingMenu();
    private JPanel container = new JPanel();
    private int compteur = 0;
    private boolean animated = true;
    private boolean backX, backY;
    private int x, y;
    private Thread t;
    //menubar
    private JMenuBar menuBar = new JMenuBar();
    private JMenu animation = new JMenu("Animation"),
            forme = new JMenu("Forme"),
            typeForme = new JMenu("Type de forme"),
            aPropos = new JMenu("A propos");
    private JMenuItem lancer = new JMenuItem("Lancer l'animation"),
            arreter = new JMenuItem("Arreter l'animation"),
            quitter = new JMenuItem("Quitter"),
            aProposItem = new JMenuItem("?");
    private JCheckBoxMenuItem morph = new
            JCheckBoxMenuItem("Morphing");
    private JRadioButtonMenuItem carre = new
            JRadioButtonMenuItem("Carre"),
            rond = new JRadioButtonMenuItem("Rond"),
            triangle = new JRadioButtonMenuItem("Triangle"),
            etoile = new JRadioButtonMenuItem("Etoile");
    private ButtonGroup bg = new ButtonGroup();

    //menu contextuel
    private JPopupMenu jpm = new JPopupMenu();
    private JMenu background = new JMenu("Couleur de fond");
    private JMenu couleur = new JMenu("Couleur de la forme");
    private JMenuItem launch = new JMenuItem("Lancer l'animation");
    private JMenuItem stop = new JMenuItem("Arreter l'animation");
    private JMenuItem rouge = new JMenuItem("Rouge"),
            bleu = new JMenuItem("Bleu"),
            vert = new JMenuItem("Vert"),
            blanc = new JMenuItem("Blanc"),
            blancBack = new JMenuItem("Blanc"),
            rougeBack = new JMenuItem("Rouge"),
            bleuBack = new JMenuItem("Bleu"),
            vertBack = new JMenuItem("Vert");

    //des listeners globaux
    private StopAnimationListener stopAnimation = new StopAnimationListener();
    private StartAnimationListener startAnimation = new StartAnimationListener();
    //Avec des listeners pour les couleurs
    private CouleurFondListener bgColor = new CouleurFondListener();
    private CouleurFormeListener frmColor = new CouleurFormeListener();

    //Création de notre barre d'outils
    private JToolBar toolBar = new JToolBar();

    //Les boutons de la barre d'outils
    private JButton play = new JButton(new ImageIcon("src/com/company/images/play.png")),
            cancel = new JButton(new ImageIcon("src/com/company/images/stop.jpeg")),
            square = new JButton(new ImageIcon("src/com/company/images/carre.png")),
            tri = new JButton(new ImageIcon("src/com/company/images/triangle.png")),
            circle = new JButton(new ImageIcon("src/com/company/images/rond.png")),
            star = new JButton(new ImageIcon("src/com/company/images/etoile.jpeg"));
    private Color fondBouton = Color.white;
    private FormeListener fListener = new FormeListener();

    public FenetreAnimationMenuConceptToolBar() {
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());

//On initialise le menu stop
        stop.setEnabled(false);
//On affecte les écouteurs
        stop.addActionListener(stopAnimation);
        launch.addActionListener(startAnimation);

//On affecte les écouteurs aux points de menu
        rouge.addActionListener(frmColor);
        bleu.addActionListener(frmColor);
        vert.addActionListener(frmColor);
        blanc.addActionListener(frmColor);

        rougeBack.addActionListener(bgColor);
        bleuBack.addActionListener(bgColor);
        vertBack.addActionListener(bgColor);
        blancBack.addActionListener(bgColor);

//On crée et on passe l'écouteur pour afficher le menu contextuel
//Création d'une implémentation de MouseAdapter
//avec redéfinition de la méthode adéquate
        pan.addMouseListener(new MouseAdapter() {
            //cette methode fonctionne pour les 2 mousepresse et mouserelease
            public void mousePressed(MouseEvent event) {
                check(event);
            }

            public void mouseReleased(MouseEvent event) {
                check(event);
            }

            //Seulement s'il s'agit d'un clic droit
            public void check(MouseEvent event) {
                if (event.isPopupTrigger()) {
//on cree la forme du menu  contextuel
                    background.add(rougeBack);
                    background.add(bleuBack);
                    background.add(vertBack);

                    couleur.add(rouge);
                    couleur.add(bleu);
                    couleur.add(vert);
                    jpm.add(launch);

                    jpm.add(stop);
                    jpm.add(couleur);
                    jpm.add(background);
//La méthode qui va afficher le menu
                    jpm.show(pan, event.getX(), event.getY());
                }
            }
        });

        container.add(pan, BorderLayout.CENTER);
        this.setContentPane(container);
//methode pour initialise menu
        this.initMenu();
//methode initialise toolbar
        this.initToolBar();
//met tout visible
        this.setVisible(true);
    }

    private void initMenu() {
//Menu Animation
/*//Cette instruction ajoute l'accélérateur 'CTR+L' à notre objet pour le lancer
lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
KeyEvent.CTRL_MASK));
animation.add(lancer);
//Ajout du listener pour arrêter l'animation
arreter.addActionListener(new StopAnimationListener());
arreter.setEnabled(false);
arreter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
animation.add(arreter);
//Ajout du listener pour lancer l'animation
lancer.addActionListener(new StartAnimationListener());
animation.add(lancer);
//Ajout du listener pour arrêter l'animation
//arreter.addActionListener(new StopAnimationListener());
//arreter.setEnabled(false);
animation.add(arreter);*/
//Ajout du listener pour lancer l'animation
//ATTENTION, LE LISTENER EST GLOBAL !!!
        lancer.addActionListener(startAnimation);
//On attribue l'accélerateur c
        lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
                KeyEvent.CTRL_MASK));
        animation.add(lancer);
//Ajout du listener pour arrêter l'animation
//LISTENER A CHANGER ICI AUSSI
        arreter.addActionListener(stopAnimation);
        arreter.setEnabled(false);
        arreter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        animation.add(arreter);
//fin ici
        animation.addSeparator();
        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        animation.add(quitter);
//Menu Forme
        bg.add(carre);
        bg.add(triangle);
        bg.add(rond);
        bg.add(etoile);
//On crée un nouvel écouteur, inutile de créer 4 instances différentes
        FormeListener fl = new FormeListener();
        carre.addActionListener(fl);
        rond.addActionListener(fl);
        triangle.addActionListener(fl);
        etoile.addActionListener(fl);
        typeForme.add(rond);
        typeForme.add(carre);
        typeForme.add(triangle);
        typeForme.add(etoile);
        rond.setSelected(true);
        forme.add(typeForme);
//Ajout du listener pour le morphing
        morph.addActionListener(new MorphListener());
        forme.add(morph);
//Menu À propos
//Ajout de ce que doit faire le "?"
        aProposItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane jop = new JOptionPane();
                ImageIcon img = new ImageIcon("images/img.png");
                String mess = "Merci ! \n J'espere que vous vous amusez bien !\n";
                mess += "Je crois qu'il est temps d'ajouter des accelerateurs et des " + " mnemoniques dans tout ça…\n";
                mess += "\n Allez, GO les ZerOs !";
                jop.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE, img);
            }
        });
        aPropos.add(aProposItem);
//Ajout des menus dans la barre de menus
        menuBar.add(animation);
        menuBar.add(forme);
        menuBar.add(aPropos);
//Ajout de la barre de menus sur la fenêtre
        this.setJMenuBar(menuBar);
    }//fin initMenu

    private void initToolBar() {
        this.cancel.setEnabled(false);
        this.cancel.addActionListener(stopAnimation);
        this.cancel.setBackground(fondBouton);
        this.play.addActionListener(startAnimation);
        this.play.setBackground(fondBouton);
        this.toolBar.add(play);
        this.toolBar.add(cancel);
        this.toolBar.addSeparator();
//Ajout des Listeners
        this.circle.addActionListener(fListener);
        this.circle.setBackground(fondBouton);
        this.toolBar.add(circle);
        this.square.addActionListener(fListener);
        this.square.setBackground(fondBouton);
        this.toolBar.add(square);
        this.tri.setBackground(fondBouton);
        this.tri.addActionListener(fListener);
        this.toolBar.add(tri);
        this.star.setBackground(fondBouton);
        this.star.addActionListener(fListener);
        this.toolBar.add(star);
        this.add(toolBar, BorderLayout.NORTH);
    }//fin inittoolbar

    private void go() {
//Les coordonnées de départ de notre rond
        x = pan.getPosX();
        y = pan.getPosY();
//Le booléen pour savoir si l'on recule ou non sur l'axe x
        boolean backX = false;
//Le booléen pour savoir si l'on recule ou non sur l'axe y
        boolean backY = false;
//Dans cet exemple, j'utilise une boucle while
//Vous verrez qu'elle fonctionne très bien
        while (this.animated) {
//Si la coordonnée x est inférieure à 1, on avance
            if (x < 1)
                backX = false;
//Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
            if (x > pan.getWidth() - 50)
                backX = true;
//Idem pour l'axe y
            if (y < 1)
                backY = false;
            if (y > pan.getHeight() - 50)
                backY = true;
//Si on avance, on incrémente la coordonnée
//backX est un booléen, donc !backX revient à écrire
//if (backX == false)
            if (!backX)
                pan.setPosX(++x);
//Sinon, on décrémente
            else
                pan.setPosX(--x);
//Idem pour l'axe Y
            if (!backY)
                pan.setPosY(++y);
            else
                pan.setPosY(--y);
//On redessine notre Panneau
            pan.repaint();
//Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }//fin while
    }//fin go

    public class StartAnimationListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null,
                    "Voulez-vous lancer l'animation ?",
                    "Lancement de l'animation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
//menuBar modidfie
                lancer.setEnabled(false);
                arreter.setEnabled(true);
//On ajoute l'instruction pour le menu contextuel
                launch.setEnabled(false);
                stop.setEnabled(true);
//on met instruction toolbar
                play.setEnabled(false);
                cancel.setEnabled(true);
//on lance animation
                animated = true;
                t = new Thread(new PlayAnimation());
                t.start();
            }
/*JOptionPane jop = new JOptionPane();
int option = jop.showConfirmDialog(null,"Voulez-vous lancer l'animation ?","Lancement de l'animation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
if(option == JOptionPane.OK_OPTION){
lancer.setEnabled(false);
arreter.setEnabled(true);
animated = true;
t = new Thread(new PlayAnimation());
t.start();
}*/
        }
    }//fin start ani. list.

    class StopAnimationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null,
                    "Voulez-vous arrêter l'animation ?",
                    "Arrêt de l'animation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option != JOptionPane.NO_OPTION && option !=
                    JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION) {
                animated = false;
//On remplace nos boutons par nos JMenuItem
                lancer.setEnabled(true);
                arreter.setEnabled(false);
//On ajoute l'instruction pour le menu contextuel
                launch.setEnabled(true);
                stop.setEnabled(false);
//ajout instruction pour toolbar
                play.setEnabled(true);
                cancel.setEnabled(false);
            }

/*JOptionPane jop = new JOptionPane();

 int option = jop.showConfirmDialog(null,"Voulez-vous arrêter l'animation ?","Arrêt de l'animation",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
if(option != JOptionPane.NO_OPTION && option !=
JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){
animated = false;
//On remplace nos boutons par nos JMenuItem
lancer.setEnabled(true);
arreter.setEnabled(false);
}*/
        }
    }//fin stop animation listener

    class FormeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//Si l'action vient d'un bouton radio du menu
            if (e.getSource().getClass().getName().equals("javax.swing.JRadioButtonMenuItem"))
                pan.setForme(((JRadioButtonMenuItem) e.getSource()).getText());
            else {
                if (e.getSource() == square) {
                    carre.doClick();
                } else if (e.getSource() == tri) {
                    triangle.doClick();
                } else if (e.getSource() == star) {
                    etoile.doClick();
                } else {
                    rond.doClick();
                }
            }
        }
    }//fin forme listener

    class PlayAnimation implements Runnable {
        public void run() {
            go();
        }

    }//fin runnable

/*class FormeListener implements ActionListener{
public void actionPerformed(ActionEvent e) {
pan.setForme(((JRadioButtonMenuItem)e.getSource()).getText());
}
}*/

    class MorphListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//Si la case est cochée, activation du mode morphing
            if (morph.isSelected()) pan.setMorph(true);
//Sinon rien !
            else pan.setMorph(false);
        }
    }//fin Morphlistener

    //Écoute le changement de couleur du fond
    class CouleurFondListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vertBack)
                pan.setCouleurFond(Color.green);
            else if (e.getSource() == bleuBack)
                pan.setCouleurFond(Color.blue);
            else if (e.getSource() == rougeBack)
                pan.setCouleurFond(Color.red);
            else
                pan.setCouleurFond(Color.white);
        }
    }//fin couleur find list.

    //Écoute le changement de couleur du fond
    class CouleurFormeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vert)
                pan.setCouleurForme(Color.green);
            else if (e.getSource() == bleu)
                pan.setCouleurForme(Color.blue);
            else if (e.getSource() == rouge)
                pan.setCouleurForme(Color.red);
            else
                pan.setCouleurForme(Color.white);
        }
    }//fin couleur forme list.
}//finfenetreAnimation