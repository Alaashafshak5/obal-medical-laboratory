/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controllers.BillController;
import Entities.Bill;
import Entities.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.pdf.PdfPTable;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import javax.swing.SwingWorker;

/**
 *
 * @author Alaa Shafshak
 */
public class BillResultFrame extends javax.swing.JFrame {

    /**
     * Creates new form BillResultFrame
     */
    int orderId;
    int clientId;

    DefaultTableModel model;
    LocalDate date = LocalDate.now();

    public BillResultFrame(int orderId, int clientId) {
        this.orderId = orderId;
        this.clientId = clientId;
        model = new DefaultTableModel();

        initComponents();

        appLabel.setText(Integer.toString(orderId));
        patientIdLabel.setText(Integer.toString(clientId));

        try {
            String ordDate = BillController.instance.getOrdDate(orderId);
            appDate.setText(ordDate);
            String first = BillController.instance.getFirstName(clientId);
            String last = BillController.instance.getLastName(clientId);
            String loc = BillController.instance.getLocation(clientId);
            String docf = BillController.instance.getDoctorFirst(orderId);
            String docl = BillController.instance.getDoctorLast(orderId);
            nameLabel.setText(first);
            lastnameLabel.setText(last);
            locationLabel.setText(loc);
            docName.setText(docf);
            docLastname.setText(docl);

            model = BillController.instance.showAnalysis(orderId);
            table.setModel(model);

            Double total = BillController.instance.getTotal();
            totalLabel.setText(total + " $");

            int idbill = BillController.instance.getBillCount();
            idBillLabel.setText(Integer.toString(idbill));

            String d = date.toString();
            dateBill.setText(d);
        } catch (SQLException ex) {
            Logger.getLogger(BillResultFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printTable(PdfPTable analysisTable) {
       
        
        analysisTable.addCell("Analysis ID");
        analysisTable.addCell("Name");
        analysisTable.addCell("Price");
        analysisTable.addCell("Participation %");
        analysisTable.addCell("Rest");
        for(int i=0;i<table.getRowCount();i++)
    {
        for(int j=0;j<table.getColumnCount();j++)
        {
            Object val = table.getModel().getValueAt(i, j);
            if((val!=null))
                analysisTable.addCell(val.toString());
            else
                analysisTable.addCell("null");

        }
    }
    }
 
    public void printToPdf() throws FileNotFoundException, DocumentException {
        String pdfName = "test22";
        Document doc = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Bills PDF\\"
                + pdfName + ".pdf"));
        System.out.println("Printing pdf...");
        doc.open();

        Font fontTest = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);

        Paragraph parag = new Paragraph();
        parag.add(new Paragraph("OBAL ANALYSIS LABORATORY - WWW.OBAL.LU \n\n",
                fontTest));

        parag.add(new Paragraph("Patient's bill Nº  " + idBillLabel.getText() + "                    " + "Date:" + dateBill.getText() + "\n \n", font2));
        parag.add(new Paragraph("Concerning order Nº" + appLabel.getText() + "                      " + "On:" + appDate.getText() + "\n \n", font2));
        parag.add(new Paragraph("Patient:           " + patientIdLabel.getText(), font2));
        parag.add(new Paragraph("                   " + nameLabel.getText() + " " + lastnameLabel.getText(), font2));
        parag.add(new Paragraph("                   " + locationLabel.getText() + "\n \n", font2));
        parag.add(new Paragraph("Doctor treating:   " + docName.getText() + " " + docLastname.getText()+"\n \n \n", font2));

     
        PdfPTable analysisTable = new PdfPTable(table.getColumnCount());
        printTable(analysisTable);
       
        
        doc.add(parag);
        doc.add(analysisTable); 
       
        Paragraph p = new Paragraph();
        p.add(new Paragraph("\n \n   "));
        p.add(new Paragraph("                                              "
                + "                                               "
                + "               Total:" + totalLabel.getText(), font1));
       
        doc.add(p);
        
        doc.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idBillLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateBill = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        appLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        appDate = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        patientIdLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        lastnameLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        docName = new javax.swing.JLabel();
        docLastname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        titleLabel.setText("OBAL ANALYSIS LABORATORY - WWW.OBAL.LU");

        jLabel1.setText("Patient's bill Nº");

        idBillLabel.setText("\"bill id\"");

        jLabel2.setText("Date:");

        dateBill.setText("\"date\"");

        jLabel3.setText("Concerning order Nº");

        appLabel.setText("\"id app\"");

        jLabel4.setText("On:");

        appDate.setText("\"date\"");

        jLabel5.setText("Patient:");

        patientIdLabel.setText("\"patient id\"");

        nameLabel.setText("\"name\"");

        lastnameLabel.setText("\"lastname\"");

        locationLabel.setText("\"location\"");

        jLabel6.setText("Doctor treating:");

        docName.setText("\"name\"");

        docLastname.setText("\"lastname\"");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Analysis ID", "Name", "Price", "Participation %", "Rest"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel7.setText("Total:");

        totalLabel.setText("0 $");

        button.setText("Print");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idBillLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)))
                                .addGap(27, 27, 27))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appLabel)
                                .addGap(151, 151, 151)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appDate)
                            .addComponent(dateBill))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalLabel)
                        .addGap(84, 84, 84))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(patientIdLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(locationLabel)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lastnameLabel))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(docName)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(docLastname))))))
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idBillLabel)
                    .addComponent(jLabel2)
                    .addComponent(dateBill))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(appLabel)
                    .addComponent(appDate)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientIdLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(lastnameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(locationLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(docName)
                    .addComponent(docLastname))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(totalLabel))
                .addGap(18, 18, 18)
                .addComponent(button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        try {
            int id = Integer.parseInt(idBillLabel.getText());
            LocalDate date = LocalDate.parse(dateBill.getText());
            int order = Integer.parseInt(appLabel.getText());
            Order o = new Order(order); 
                           
            printToPdf();
            
            new AddFacture(new Bill(id,date,o)).execute();
            
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(BillResultFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
     private class AddFacture extends SwingWorker<String, Void>{
        Bill b;

        public AddFacture(Bill b) {
            this.b = b;
        }
        
        
        @Override
        protected String doInBackground() throws Exception {
            BillController.instance.insertFacture(b);
            return null;
        }
        
        @Override
        public void done(){
            
        }
    }
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(BillResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new BillResultFrame(orderId, clientId).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appDate;
    private javax.swing.JLabel appLabel;
    private javax.swing.JButton button;
    private javax.swing.JLabel dateBill;
    private javax.swing.JLabel docLastname;
    private javax.swing.JLabel docName;
    private javax.swing.JLabel idBillLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lastnameLabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel patientIdLabel;
    private javax.swing.JTable table;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
