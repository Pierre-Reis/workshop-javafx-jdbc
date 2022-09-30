package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable{

	private Department department;
	private DepartmentService service;
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if(department == null) {
			throw new IllegalStateException("Department was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			department = getFormData();
			service.saveOrUpdate(department);
			Utils.currentStage(event).close();
		}
		catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Department getFormData() {
		Department dep = new Department();
		
		dep.setId(Utils.tryParseToInt(txtId.getText()));
		dep.setName(txtName.getText());
		
		return dep;
	}
	
	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	public void updateDataForm() {
		
		txtId.setText(String.valueOf(department.getId()));
		txtName.setText(department.getName());
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		initializableNodes();
	}	
	
	public void initializableNodes() {
		
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
}
