import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeMenuForm {



    private JFrame frame;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;

    public WelcomeMenuForm() {
        frame = new JFrame("Welcome to IMT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());


        JLabel titleLabel = new JLabel("Welcome to IMT ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(44, 62, 80));

        JLabel instructionLabel = new JLabel("For every menu, you'll have a few options to choose from.");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setForeground(new Color(44, 62, 80));

        JLabel contactLabel = new JLabel("For any questions, contact us at 217 949 000");
        contactLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contactLabel.setForeground(new Color(44, 62, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        centerPanel.add(instructionLabel, gbc);

        gbc.gridy++;
        centerPanel.add(contactLabel, gbc);


        JPanel buttonPanel = new JPanel(new GridBagLayout());


        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(6, 65, 16));
        loginButton.setForeground(Color.white);
        loginButton.setPreferredSize(new Dimension(120, 40));
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(65, 13, 11));
        registerButton.setForeground(Color.white);
        registerButton.setPreferredSize(new Dimension(120, 40));
        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(32, 32, 32));
        exitButton.setForeground(Color.white);
        exitButton.setPreferredSize(new Dimension(120, 40));

        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.insets = new Insets(10, 10, 10, 10);
        buttonGbc.anchor = GridBagConstraints.CENTER;

        buttonPanel.add(loginButton, buttonGbc);
        buttonGbc.gridy++;
        buttonPanel.add(registerButton, buttonGbc);
        buttonGbc.gridy++;
        buttonPanel.add(exitButton, buttonGbc);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoginButton();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegisterButton();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void handleLoginButton() {
        frame.dispose();
        LoginForm loginForm = new LoginForm();
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginFrame.add(loginForm);
        loginFrame.setVisible(true);
    }

    private void handleRegisterButton() {
        frame.dispose();
        RegisterForm registerForm = new RegisterForm();
        JFrame registerFrame = new JFrame("RegisterForm");
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        registerFrame.add(registerForm);
        registerFrame.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WelcomeMenuForm welcomeMenuForm = new WelcomeMenuForm();
                welcomeMenuForm.show();
            }
        });
    }
}
