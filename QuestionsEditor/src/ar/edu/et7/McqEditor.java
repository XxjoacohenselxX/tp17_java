package ar.edu.et7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class McqEditor extends JFrame {
    private JTextField txtTitle, txtCategory, txtStimulus, txtPrompt, txtPoints;
    private JTextField[] txtChoices = new JTextField[4];
    private JTextField txtAnswers;
    private JButton btnSave, btnNext, btnInsert, btnDelete;
    private JFileChooser fileChooser;
    private ArrayNode questionsArray;  // Usaremos ArrayNode para manejar el array de preguntas
    private int currentIndex = -1;
    private File currentFile;
    private ObjectMapper objectMapper; // Jackson ObjectMapper para manejar JSON

    public McqEditor() {
        objectMapper = new ObjectMapper();  // Inicializamos el ObjectMapper

        setTitle("MCQ Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        formPanel.add(new JLabel("Title:"));
        txtTitle = new JTextField();
        formPanel.add(txtTitle);

        formPanel.add(new JLabel("Category:"));
        txtCategory = new JTextField();
        formPanel.add(txtCategory);

        formPanel.add(new JLabel("Stimulus:"));
        txtStimulus = new JTextField();
        formPanel.add(txtStimulus);

        formPanel.add(new JLabel("Prompt:"));
        txtPrompt = new JTextField();
        formPanel.add(txtPrompt);

        formPanel.add(new JLabel("Points:"));
        txtPoints = new JTextField();
        formPanel.add(txtPoints);

        for (int i = 0; i < 4; i++) {
            formPanel.add(new JLabel("Choice " + (char) ('A' + i) + ":"));
            txtChoices[i] = new JTextField();
            formPanel.add(txtChoices[i]);
        }

        formPanel.add(new JLabel("Answers:"));
        txtAnswers = new JTextField();
        formPanel.add(txtAnswers);

        // Crear botones
        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("Save");
        btnNext = new JButton("Next");
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnSave);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnDelete);

        // Añadir los paneles al frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");

        JMenuItem menuOpen = new JMenuItem("Open");
        JMenuItem menuSave = new JMenuItem("Save");
        JMenuItem menuSaveAs = new JMenuItem("Save As...");

        menuFile.add(menuOpen);
        menuFile.add(menuSave);
        menuFile.add(menuSaveAs);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);

        // Funcionalidad de los botones
        menuOpen.addActionListener(e -> openFile());
        menuSave.addActionListener(e -> saveFile(false));
        menuSaveAs.addActionListener(e -> saveFile(true));
        btnSave.addActionListener(e -> saveCurrentQuestion());
        btnNext.addActionListener(e -> nextQuestion());
        btnInsert.addActionListener(e -> insertNewQuestion());
        btnDelete.addActionListener(e -> deleteCurrentQuestion());
    }

    // Método para abrir un archivo JSON
    private void openFile() {
        fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try {
                questionsArray = (ArrayNode) objectMapper.readTree(currentFile);  // Cargar el JSON como ArrayNode
                currentIndex = 0;
                loadQuestion(currentIndex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para guardar el archivo JSON
    private void saveFile(boolean saveAs) {
        if (currentFile == null || saveAs) {
            fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            }
        }

        if (currentFile != null) {
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(currentFile, questionsArray);
                JOptionPane.showMessageDialog(this, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para cargar una pregunta del JSON
    private void loadQuestion(int index) {
        if (index >= 0 && index < questionsArray.size()) {
            JsonNode question = questionsArray.get(index);
            txtTitle.setText(question.get("title").asText());
            txtCategory.setText(question.get("category").asText());
            txtStimulus.setText(question.get("stimulus").asText());
            txtPrompt.setText(question.get("prompt").asText());
            txtPoints.setText(String.valueOf(question.get("points").asInt()));

            ArrayNode choices = (ArrayNode) question.get("choices");
            for (int i = 0; i < choices.size(); i++) {
                txtChoices[i].setText(choices.get(i).get("content").asText());
            }

            ArrayNode answers = (ArrayNode) question.get("answers").get(0);
            //FIXME txtAnswers.setText(String.join(",", getArrayAsStringList(answers)));
        }
    }

    // Método para guardar la pregunta actual en el JSON
    private void saveCurrentQuestion() {
        if (currentIndex >= 0 && currentIndex < questionsArray.size()) {
            ObjectNode question = (ObjectNode) questionsArray.get(currentIndex);
            question.put("title", txtTitle.getText());
            question.put("category", txtCategory.getText());
            question.put("stimulus", txtStimulus.getText());
            question.put("prompt", txtPrompt.getText());
            question.put("points", Integer.parseInt(txtPoints.getText()));

            ArrayNode choices = objectMapper.createArrayNode();
            for (int i = 0; i < 4; i++) {
                ObjectNode choice = objectMapper.createObjectNode();
                choice.put("id", String.valueOf((char) ('a' + i)));
                choice.put("content", txtChoices[i].getText());
                choices.add(choice);
            }
            question.set("choices", choices);

            ArrayNode answers = objectMapper.createArrayNode();
            ArrayNode answerList = objectMapper.createArrayNode();
            for (String answer : txtAnswers.getText().split(",")) {
                answerList.add(answer.trim());
            }
            answers.add(answerList);
            question.set("answers", answers);
        }
    }

    // Método para ir a la siguiente pregunta
    private void nextQuestion() {
        if (currentIndex < questionsArray.size() - 1) {
            saveCurrentQuestion();
            currentIndex++;
            loadQuestion(currentIndex);
        } else {
            JOptionPane.showMessageDialog(this, "No more questions.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para insertar una nueva pregunta
    private void insertNewQuestion() {
        saveCurrentQuestion();
        ObjectNode newQuestion = objectMapper.createObjectNode();
        newQuestion.put("title", "");
        newQuestion.put("category", "");
        newQuestion.put("stimulus", "");
        newQuestion.put("prompt", "");
        newQuestion.put("points", 0);
        newQuestion.set("choices", objectMapper.createArrayNode());
        newQuestion.set("answers", objectMapper.createArrayNode());
        questionsArray.add(newQuestion);
        currentIndex = questionsArray.size() - 1;
        loadQuestion(currentIndex);
    }

    // Método para eliminar la pregunta actual
    private void deleteCurrentQuestion() {
        if (currentIndex >= 0 && currentIndex < questionsArray.size()) {
            questionsArray.remove(currentIndex);
            if (currentIndex > 0) currentIndex--;
            loadQuestion(currentIndex);
        }
    }

    // Utilidad para convertir ArrayNode a lista de Strings
//    private List<String> getArrayAsStringList(ArrayNode arrayNode) {
//        List<String> list = new ArrayList<>();
//        for (JsonNode node : arrayNode) {
//            list.add(node.asText());
//        }
//        return list;
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new McqEditor().setVisible(true);
        });
    }
}
