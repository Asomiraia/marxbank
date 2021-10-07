package it1901;

import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import it1901.jobs.TransactionJob;
import it1901.util.TextFieldFormatter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class TransactionController {

    private User user;
    private Account from;
    private Account reciever;
    private double amount;
    private String dateString;
    private DataManager dm;

    private Transaction t;

    @FXML private MenuButton myAccountsList;
    @FXML private TextField recieverText;
    @FXML private TextField dateText;
    @FXML private TextField amountText;
    @FXML private Label transactionCompleteMsg;

    private EventHandler<ActionEvent> accountsMenuEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e)
        {
            myAccountsList.setText(((MenuItem)e.getSource()).getText());
        }
    };

    @FXML 
    private void initialize() {
        transactionCompleteMsg.setVisible(false);
        setNumericOnlyTextFields();
    }

    public void initData(User user, DataManager dm) {
        this.user = user;
        this.dm = dm;
        createMyAccountsListItems();
    }

    private void createMyAccountsListItems() {
        user.getAccounts().forEach(acc -> {
            MenuItem item = new MenuItem(String.valueOf(acc.getAccountNumber()));
            item.setOnAction(accountsMenuEvent);
            myAccountsList.getItems().add(item);
        });
    }
    private void setNumericOnlyTextFields() {     
        recieverText.setTextFormatter(TextFieldFormatter.getNumberFormatter());
        amountText.setTextFormatter(TextFieldFormatter.getNumberFormatter());
    }

    @FXML
    private void handleCommitTransaction(ActionEvent ev) {
        try {
            from = Bank.getInstanceBank().getAccount(Integer.parseInt(myAccountsList.getText()));
            reciever = Bank.getInstanceBank().getAccount(Integer.parseInt(recieverText.getText()));
            amount = Integer.parseInt(amountText.getText());
            dateString = dateText.getText();

            if (dateString.isBlank()) {
                t = new Transaction(UUID.randomUUID().toString(), from, reciever, amount, dm, true);
                transactionCompleteMsg.setVisible(true);
            } else {
                t = new Transaction(from, reciever, amount, dateString, dm);
                transactionCompleteMsg.setVisible(true);
                System.out.println(from.getReservedTransactions());
                scheduleJob();
            }
            try {
                dm.save();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.err.println("Input is not a number " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        } 
        
    }

    private void scheduleJob() {
        try {
            Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
            sc.start();

            JobDetail job = JobBuilder.newJob(TransactionJob.class)
                .withIdentity("transaction")
                .build();

            Trigger tr = TriggerBuilder.newTrigger()
                .withIdentity("transactionTrigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                    .simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever())
                .build();
            
            sc.scheduleJob(job, tr);

        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
