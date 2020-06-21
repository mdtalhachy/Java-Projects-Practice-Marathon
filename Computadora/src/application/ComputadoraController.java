package application;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

class Calculation{
	public int cal(int num1, int num2, String operator) {
		switch(operator) {
		
		case "+":
			return num1 + num2;		
		case "-":
			return num1-num2;			
		case "*":
			return num1 * num2;			
		case "/":
			if(num2 == 0) return 0;
			return (num1 / num2);			
		default:
			break;
			
		}
		
		return 0;
	}
}

public class ComputadoraController implements Initializable{
	
	@FXML
	private Label label;
	private int num1 = 0;
	private String operator = "";
	private boolean flag = true;
	Calculation calc = new Calculation();
	
	@FXML
	public void numbers(ActionEvent e) {
		if(flag) {
			label.setText("");
			flag = false;
		}
		String val = ((Button) e.getSource()).getText();
		label.setText(label.getText() + val);
	}
	
	@FXML
	public void operators(ActionEvent e) {
		String val = ((Button) e.getSource()).getText();
		
		if(!val.equals("=")) {
			if(!operator.isEmpty()) {
				return;
			}
			
			operator = val;
			num1 = Integer.parseInt(label.getText());
			label.setText("");
		}else {
			if(operator.isEmpty())
				return;
			
			int num2 = Integer.parseInt(label.getText());
			int output = calc.cal(num1, num2, operator);
			label.setText(String.valueOf(output));
			operator = "";
			flag = true;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
