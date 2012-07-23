/********************************************************************************************************************************************************
Program Name			:	AdministrateMealControl.java
Description				:	A AdministrateMealControl class that is the Controller for CRUD of Meal record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	AdministrateMealControl();
						: 	AdministrateMealControl(String, String, String, double, double, double, boolean, int, boolean)
						:	processCreateMeal() : String
						:	processDeleteMeal(String) : Boolean
						:	processUpdateMeal(String) : Boolean
						:	processRetrieveMeal() : DefaultTableModel 
						:	processRetrieveMeal(String) : DefaultTableModel 
						:	procesRetrieveMealByID(String) : Meal
						:	processRetrieveMealMenu(String) : DefaultTableModel
						:	processRetrieveMealMenuByID(String) : DefaultTableModel
						:	processCalculatePrice() : Double
						:	processCalculateFinalPrice(Double) : Double
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Controller.SOM;

import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;
import Model.Meal;
import Model.MealMenu;

public class AdministrateMealControl {

	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Meal meal;				//this stores a meal object from the view or model
	private MealMenu mealMenu;				
	private DefaultTableModel model; 
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public AdministrateMealControl(){}
	public AdministrateMealControl(String Title, String Description, String Type, double PricePerHead, double Discount, double FinalPrice ,boolean Availability, int Hits, boolean isRecord){
		Meal meal= new Meal();
		meal.setMealTitle(Title);
		meal.setMealDescription(Description);
		meal.setMealType(Type);
		meal.setMealPricePerHead(PricePerHead);
		meal.setMealDiscount(Discount);
		meal.setMealFinalPrice(FinalPrice);
		meal.setMealAvailability(Availability);
		meal.setMealHits(Hits);
		meal.setRecord(isRecord);
		this.meal=meal;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/

	/********************************************************
	 * Method Name 		: processCreateMeal()
	 * Input Parameter 	: void
	 * Return 			: String 
	 * Purpose 			: To create a Meal record
	 *******************************************************/
	public String processCreateMeal(){
		String ID="";
		ID=this.meal.createMeal();
		
		//CHECKS IF CREATTION WAS SUCCESSFULL
		if(ID.equals(null)){
			return null;
		}
		else{
			boolean success=false;
			//FOR LOOP TO  CREATE THE MEAL MENU
			boolean Vegetarian=false;
			boolean halal=false;
				for(int i=0;i<model.getRowCount();i++){
					if(model.getValueAt(i, 2).equals(true))
						halal=true;
					if(model.getValueAt(i, 2).equals(false))
						halal=false;
					if(model.getValueAt(i, 3).equals(false))
						Vegetarian=false;
					if(model.getValueAt(i, 3).equals(false))
						Vegetarian=false;
				mealMenu = new MealMenu(ID,model.getValueAt(i, 0).toString(),model.getValueAt(i, 4).toString(),Double.parseDouble((String) model.getValueAt(i, 1)),halal,Vegetarian);
				success=mealMenu.createMealMenu();
			}
			if(!success){
				this.meal.deleteMeal(ID);
				ID=null;
			}
		}
		return ID;
	}

	/********************************************************
	  * Method Name 	: processDeleteMeal()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose			: To delete an Meal record
	  *******************************************************/
	public boolean processDeleteMeal(String ID){
		boolean success=true;
		Meal meal= new Meal();
		if(meal.checkRelationship(ID)){
			return false;
		}
		else{
			success=meal.deleteMeal(ID);
			MealMenu mealMenu=new MealMenu();
			success=mealMenu.deleteMealMenu(ID);
		}
		return success;
	}
	
	/********************************************************
	  * Method Name 	: processUpdateMeal()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To update an Meal record
	  *******************************************************/
	public boolean processUpdateMeal(String ID){
		boolean success=true;
		//UPDATES THE MEAL RECORD
		success=this.meal.updateMeal(ID);
		
		if(success){
			//DELETES THE MEAL MENU RECORD
			MealMenu menu=new MealMenu();
			success=menu.deleteMealMenu(ID);
			if(success){
				//RE-CREATE THE MEAL MENU RECORD
				success=false;
				//FOR LOOP TO CREATE THE MEAL MENU RECORD
				boolean Vegetarian=false;
				boolean halal=false;
					for(int i=0;i<model.getRowCount();i++){
						if(model.getValueAt(i, 2).equals(true))
							halal=true;
						if(model.getValueAt(i, 2).equals(false))
							halal=false;
						if(model.getValueAt(i, 3).equals(false))
							Vegetarian=false;
						if(model.getValueAt(i, 3).equals(false))
							Vegetarian=false;
					mealMenu = new MealMenu(ID,model.getValueAt(i, 0).toString(),model.getValueAt(i, 4).toString(),Double.parseDouble((String) model.getValueAt(i, 1)),halal,Vegetarian);
					success=mealMenu.createMealMenu();
					}
					if(!success){
						System.out.println("Fails to re-create the meal menu record");
					}
			}
			else{
				System.out.println("Fails to delete the meal menu record");
				return false;
			}
		}
		else{
			System.out.println("Fails to Update the meal record");
			return false;
		}
		return success;
	}
	
	 /********************************************************
	  * Method Name 	: processRsetrieveMeal
	  * Input Parameter : void
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all Meal record where
	  * 				  is not a purchase record
	  *******************************************************/
	public DefaultTableModel processRetrieveMeal(){
		Meal meal= new Meal();
		return meal.retrieveMeal();
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveMeal
	  * Input Parameter : String
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all meal record where 
	  * 				  is not a purchase record &
	  * 				  like input parameter
	  *******************************************************/
	public DefaultTableModel processRetrieveMeal(String parameter){
		Meal meal= new Meal();
		return meal.retrieveMeal(parameter);
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveMealByID
	  * Input Parameter : String
	  * Return 			: Entertainment
	  * Purpose 		: To retrieve a meal record by ID
	  *******************************************************/
	public Meal procesRetrieveMealByID(String ID){
		Meal meal= new Meal();
		this.meal=meal.retrieveMealByID(ID);
		MealMenu menu= new MealMenu();
		this.model=menu.retrieveMealMenuByID(ID);
		return this.meal;
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveMealMenu()
	  * Input Parameter : String
	  * Purpose 		: To retrieve EntertainmentMenu record 
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel processRetrieveMealMenu(String parameter){
		MealMenu menu= new MealMenu();
		return menu.retrieveMealMenu(parameter);
	}

	/********************************************************
	  * Method Name 	: processRetrieveMealMenuByID()
	  * Input Parameter : String
	  * Purpose 		: To retrieve EntertainmentMenu record 
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel processRetrieveMealMenuByID(String ID){
		MealMenu menu= new MealMenu();
		return menu.retrieveMealMenuByID(ID);
	}
	
	//BUSINESS LOGICS BELOW
	/********************************************************
	 * Method Name : processCalculatePrice
	 * Input Parameter : NIL 
	 * Purpose : To caculate the price before discount
	 * Return :double
	 * Tested : Success
	 *******************************************************/
	public double processCalculatePrice(){
		double price=0;
		DecimalFormat fmt = new DecimalFormat("0.00");
		for(int i=0;i<model.getRowCount();i++){
			price=price+Double.parseDouble((String) model.getValueAt(i, 1));
		}
		return Double.parseDouble(fmt.format(price));
	}
	
	/********************************************************
	 * Method Name : processCalculateFinalPrice
	 * Input Parameter : NIL 
	 * Purpose : To caculate the Final price after discount
	 * Return :double
	 * Tested : Success
	 * *******************************************************/
	public double processCalculateFinalPrice(double value){
		DecimalFormat fmt = new DecimalFormat("0.00");
		double price=processCalculatePrice();
		double discount=value;
		double finalPrice=0;
		finalPrice=price-((discount/100)*price);
		return  Double.parseDouble(fmt.format(finalPrice));
	}

	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public MealMenu getMealMenu() {
		return mealMenu;
	}
	public void setMealMenu(MealMenu mealMenu) {
		this.mealMenu = mealMenu;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
}
