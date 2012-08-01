package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import entity.FeedbackResult;
import entity.OpenEndedResult;
import entity.RatingResult;
import entity.YesNoResult;

public class AdministrateFeedbackResultController {
	
	private String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public FeedbackResult processSearchTerm(int frCode, Date creationDate, int ffCode){
		OpenEndedResult oer = new OpenEndedResult();
		String condition = null;
		
		if (frCode == 0 && creationDate == null && ffCode == 0){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (frCode != 0){
			searchTerm.add("code = " + frCode);
		}
		
		if (creationDate != null){
			searchTerm.add("creationDate = '" + dateToString(creationDate) + "'");
		}
		
		if (ffCode != 0){
			searchTerm.add("ffCode = " + ffCode);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		oer.retrieveFeedbackResult(condition);
		return oer;
	}
	
	public boolean processCreate(int ffCode){
		OpenEndedResult oer = new OpenEndedResult();
		return oer.createFeedbackResult(dateToString((Date) Calendar.getInstance().getTime()), ffCode);
	}
	
	public int processLastCode(){
		OpenEndedResult oer = new OpenEndedResult();
		return oer.retrieveLastCode();
	}
	
	public boolean processCreateYesNoResult(int frCode, int fqCode, boolean result){
		YesNoResult ynr = new YesNoResult();
		return ynr.createYesNoResult(frCode, fqCode, result);
	}
	
	public boolean processCreateRatingResult(int frCode, int fqCode, int result){
		RatingResult rr = new RatingResult();
		return rr.createRatingResult(frCode, fqCode, result);
	}
	
	public YesNoResult processYesNoResultSearchTerm(int frCode, int fqCode, boolean search, boolean result){
		YesNoResult ynr = new YesNoResult();
		String condition = null;
		
		if (frCode == 0 && fqCode == 0 && !search){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (frCode != 0){
			searchTerm.add("frCode = " + frCode);
		}
		
		if (fqCode != 0){
			searchTerm.add("fqCode = " + fqCode);
		}
		
		if (search){
			searchTerm.add("result = " + result);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		ynr.retrieveYesNoResult(condition);
		return ynr;		
	}
	
	public YesNoResult processYesNoResultSearchTerm(ArrayList<Integer> frArr, int fqCode, boolean search, boolean result){
		YesNoResult ynr = new YesNoResult();
		String condition = null;
		
		if ( ( frArr.isEmpty() || frArr == null ) && fqCode == 0 && !search){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (!frArr.isEmpty() && frArr != null){
			String frCode = "frCode in (";
			
			for (int x = 0; x < frArr.size(); x++){
				frCode += frArr.get(x).toString();
				
				if (x != frArr.size() - 1){
					frCode += ", ";
				}
			}
			
			frCode += ")";
			searchTerm.add(frCode);
		}
		
		if (fqCode != 0){
			searchTerm.add("fqCode = " + fqCode);
		}
		
		if (search){
			searchTerm.add("result = " + result);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		ynr.retrieveYesNoResult(condition);
		return ynr;		
	}
	
	public RatingResult processRatingResultSearchTerm(int frCode, int fqCode, int result){
		RatingResult rr = new RatingResult();
		String condition = null;
		
		if (frCode == 0 && fqCode == 0 && result == 0){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (frCode != 0){
			searchTerm.add("frCode = " + frCode);
		}
		
		if (fqCode != 0){
			searchTerm.add("fqCode = " + fqCode);
		}
		
		if (result != 0){
			searchTerm.add("result = " + result);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		rr.retrieveRatingResult(condition);
		return rr;
	}
	
	public RatingResult processRatingResultSearchTerm(ArrayList<Integer> frArr, int fqCode, int result){
		RatingResult rr = new RatingResult();
		String condition = null;
		
		if ( ( frArr.isEmpty() || frArr == null ) && fqCode == 0 && result == 0){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (!frArr.isEmpty() && frArr != null){
			String frCode = "frCode in (";
			
			for (int x = 0; x < frArr.size(); x++){
				frCode += frArr.get(x).toString();
				
				if (x != frArr.size() - 1){
					frCode += ", ";
				}
			}
			
			frCode += ")";
			searchTerm.add(frCode);
		}
		
		if (fqCode != 0){
			searchTerm.add("fqCode = " + fqCode);
		}
		
		if (result != 0){
			searchTerm.add("result = " + result);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		rr.retrieveRatingResult(condition);
		return rr;
	}
	
	public OpenEndedResult processOpenEndedResultSearchTerm(int frCode, int fqCode, ArrayList<String> keyword){
		OpenEndedResult oer = new OpenEndedResult();
		String condition = null;
		
		if (frCode == 0 && fqCode == 0 && keyword == null){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (frCode != 0){
			searchTerm.add("frCode = " + frCode);
		}
		
		if (fqCode != 0){
			searchTerm.add("fqCode = " + fqCode);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		oer.retrieveOpenEndedResult(condition);
		
		if (keyword != null){
			oer = processKeyword(keyword, oer);
		}
		
		return oer;
	}
	
	public OpenEndedResult processOpenEndedResultSearchTerm(ArrayList<Integer> frArr, int fqCode, ArrayList<String> keyword){
		OpenEndedResult oer = new OpenEndedResult();
		String condition = null;
		
		if ( ( frArr.isEmpty() || frArr == null ) && fqCode == 0 && keyword == null){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (!frArr.isEmpty() && frArr != null){
			String frCode = "frCode in (";
			
			for (int x = 0; x < frArr.size(); x++){
				frCode += frArr.get(x).toString();
				
				if (x != frArr.size() - 1){
					frCode += ", ";
				}
			}
			
			frCode += ")";
			searchTerm.add(frCode);
		}
		
		if (fqCode != 0){
			searchTerm.add("fqCode = " + fqCode);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		oer.retrieveOpenEndedResult(condition);
		
		if (keyword != null){
			oer = processKeyword(keyword, oer);
		}
		
		return oer;
	}
	
	public OpenEndedResult processKeyword(ArrayList<String> keyword, OpenEndedResult oer){
		
		if (oer == null){
			oer = new OpenEndedResult();
			oer.retrieveOpenEndedResult(null);
		}
		
		ArrayList<Integer> fqNumber = new ArrayList<Integer>();

		for (int i = 0; i < keyword.size(); i++){
			for (int x = 0; x < oer.getResult().size(); x++){
				String temp = oer.getResult().get(x);
				temp = temp.replaceAll("\\W", " ");
				Scanner sc = new Scanner(temp);
				sc.useDelimiter(" ");
				
				while (sc.hasNext()){
					if (keyword.get(i).equalsIgnoreCase(sc.next())){
						fqNumber.add(x);
						break;
					}
				}
			}
		}
		
		for (int i = 0; i < fqNumber.size(); i++){
			int first = fqNumber.get(i);
			
			for (int x = i + 1; x < fqNumber.size(); x++){
				int second = fqNumber.get(x);
				
				if (first == second){
					fqNumber.remove(x);
					x -= 1;
				}
			}
		}

		OpenEndedResult newOer = new OpenEndedResult();
		
		for (int a = 0; a < fqNumber.size(); a++){
			int index = fqNumber.get(a);
			newOer.setCode(oer.getCode().get(index));
			newOer.setFfCode(oer.getFfCode().get(index));
			newOer.setResult(oer.getResult().get(index));
		}
		
		return newOer;
	}
	
	public RatingResult processRetrieveRatingResult(){
		RatingResult rr = new RatingResult();
		rr.retrieveRatingResult(null);
		return rr;
	}
	
	public YesNoResult processRetrieveYesNoResult(){
		YesNoResult ynr = new YesNoResult();
		ynr.retrieveYesNoResult(null);
		return ynr;
	}
	
	public boolean processCreateOpenEndedResult(int frCode, int fqCode, String result){
		OpenEndedResult oer = new OpenEndedResult();
		return oer.createOpenEndedResult(frCode, fqCode, result);
	}

	public OpenEndedResult processRetrieveOpenEndedResult(){
		OpenEndedResult oer = new OpenEndedResult();
		oer.retrieveOpenEndedResult(null);
		return oer;
	}
	
	public ArrayList<String> processRetrieveWords(){
		OpenEndedResult oer = new OpenEndedResult();
		oer = processRetrieveOpenEndedResult();
		
		ArrayList<String> wordArr = new ArrayList<String>();

		for (int i = 0; i < oer.getCode().size(); i++){
			String temp = oer.getResult().get(i).toLowerCase();
			temp = temp.replaceAll("\\W", " ");
			temp = temp.replaceAll("\\s+", " ");
			Scanner sc = new Scanner(temp);
			sc.useDelimiter(" ");
			
			while (sc.hasNext()){
				wordArr.add(sc.next());
			}
		}
		
		Collections.sort(wordArr);
		
		for (int a = 0; a < wordArr.size(); a++){
			String first = wordArr.get(a);
			
			for (int b = a + 1; b < wordArr.size(); b++){
				String second = wordArr.get(b);
				
				if (first.equals(second)){
					wordArr.remove(b);
					b -= 1;
				}
			}
		}
		
		return wordArr;
	}
}
