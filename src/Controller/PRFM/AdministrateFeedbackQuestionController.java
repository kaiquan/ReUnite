package Controller.PRFM;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import Model.PRFM.FeedbackQuestion;
import Model.PRFM.RatingQuestion;
import Model.PRFM.YesNoQuestion;

public class AdministrateFeedbackQuestionController {

	public FeedbackQuestion processKeyword(ArrayList<String> keyword, FeedbackQuestion fq){
		
		if (fq == null){
			fq = new FeedbackQuestion();
			fq.retrieveFeedbackQuestion(null);
		}
		
		ArrayList<Integer> fqNumber = new ArrayList<Integer>();

		for (int i = 0; i < keyword.size(); i++){
			for (int x = 0; x < fq.getQuestion().size(); x++){
				String temp = fq.getQuestion().get(x);
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

		FeedbackQuestion newFq = new FeedbackQuestion();
		
		for (int a = 0; a < fqNumber.size(); a++){
			int index = fqNumber.get(a);
			newFq.setCode(fq.getCode().get(index));
			newFq.setCreationDate(fq.getCreationDate().get(index));
			newFq.setType(fq.getType().get(index));
			newFq.setQuestion(fq.getQuestion().get(index));
		}
		
		return newFq;
	}
	
	private String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public boolean processCreate(String type, String question){
		FeedbackQuestion fq = new FeedbackQuestion();
		return fq.createFeedbackQuestion(dateToString((Date) Calendar.getInstance().getTime()), type, question);
	}
	
	public FeedbackQuestion processSearchTerm(int code, Date creationDate, String type, ArrayList<String> keyword){
		FeedbackQuestion fq = new FeedbackQuestion();
		String condition = null;
		
		if (code == 0 && creationDate == null && type == null && keyword == null){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (code != 0){
			searchTerm.add("code = " + code);
		}
		
		if (creationDate != null){
			searchTerm.add("creationDate = '" + dateToString(creationDate) + "'");
		}
		
		if (type != null){
			searchTerm.add("type = '" + type + "'");
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		if (code == 0 && creationDate == null && type == null){
			fq = null;
		}
		else{
			fq.retrieveFeedbackQuestion(condition);
		}

		if (keyword != null){
			fq = processKeyword(keyword, fq);
		}
			
		return fq;
	}
	
	public FeedbackQuestion processRetrieve(){
		FeedbackQuestion fq = new FeedbackQuestion();
		fq.retrieveFeedbackQuestion(null);
		return fq;
	}
	
	public boolean processUpdate(int code, String type, String question){
		FeedbackQuestion fq = new FeedbackQuestion();
		return fq.updateFeedbackQuestion(code, type, question);
	}
	
	public boolean processDelete(int code){
		FeedbackQuestion fq = new FeedbackQuestion();
		return fq.deleteFeedbackQuestion(code);
	}
	
	public int processLastCode(){
		FeedbackQuestion fq = new FeedbackQuestion();
		return fq.retrieveLastCode();
	}
	
	public YesNoQuestion processRetrieveYesNoQuestion(){
		YesNoQuestion ynq = new YesNoQuestion();
		ynq.retrieveYesNoQuestion();
		return ynq;
	}
	
	public RatingQuestion processRetrieveRatingQuestion(){
		RatingQuestion rq = new RatingQuestion();
		RatingQuestion newRq = new RatingQuestion();
		rq.retrieveRatingQuestion();
		
		for (int i = 1; i <= rq.getChoice().size(); i++){
			for (int x = 0; x <= rq.getChoice().size(); x++){
				if (rq.getRating().get(x) == i){
					newRq.setChoice(rq.getChoice().get(x));
					newRq.setRating(rq.getRating().get(x));
					break;
				}
			}
		}
		
		return newRq;
	}

	public ArrayList<String> processRetrieveWords(){
		FeedbackQuestion fq = new FeedbackQuestion();
		fq = processRetrieve();
		
		ArrayList<String> wordArr = new ArrayList<String>();

		for (int i = 0; i < fq.getCode().size(); i++){
			String temp = fq.getQuestion().get(i).toLowerCase();
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
	
	public boolean processUpdateRatingQuestion(int rating, String choice){
		if (rating == 0 & choice == null){
			return false;
		}
		
		RatingQuestion rq = new RatingQuestion();
		String condition = " SET choice = '" + choice + "' WHERE rating = " + rating;
		return rq.updateRatingQuestion(condition);
		
	}
}
