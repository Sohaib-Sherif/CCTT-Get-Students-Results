package ly.sohaib.seleniumCollegeID;

import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import static org.jooq.impl.DSL.*;

import java.util.ArrayList;
import java.util.List;

public class DBUtils {
	private static DSLContext context = DSL.using(DB.getConnection(), SQLDialect.H2);
	private static int ID; 
	private static String NAME;
	private static String SEMESTER; 
	private static String DEPARTMENT;
	private static List<String> CODE = new ArrayList<>();
	private static List<String> GRADE = new ArrayList<>();
	private static double SEMESTER_PERCENTAGE;
	private static double OVERALL_PERCENTAGE;
	private static List<String> SUBJECTNAME = new ArrayList<>();
	// add a year خريف2017 column to the table.

	
	public static void updateStudentTable() {
		try{
			context.insertInto(table("INFORMATION_SCHEMA.STUDENT"))
				   .values(ID, NAME, SEMESTER, DEPARTMENT)
			       .execute();
		} catch (DataAccessException db) {
			db.printStackTrace();
		}
					 
	}
	
	public static void updateResultTable() {
		for(int i = 0; i<CODE.size(); i++) {
			context.insertInto(table("INFORMATION_SCHEMA.RESULT"))
				   .values(ID, CODE.get(i), SUBJECTNAME.get(i), GRADE.get(i))
				   .execute();
		}
		CODE.clear();
		SUBJECTNAME.clear();
		GRADE.clear();// I did this because the info from the last student went on with the one on next.
	}
	
	public static void updatePercentageTable() {
		context.insertInto(table("INFORMATION_SCHEMA.PERCENTAGE"))
			   .values(ID, SEMESTER_PERCENTAGE, OVERALL_PERCENTAGE)
			   .execute();
	}

	public static DSLContext getContext() {
		return context;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	public static void setNAME(String nAME) {
		NAME = nAME;
	}

	public static void setSEMESTER(String sEMESTER) {
		SEMESTER = sEMESTER;
	}

	public static void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}

	public static void addCODE(String cODE) {
		CODE.add(cODE);
	}
	
	public static void addSubjectName(String SubjectName) {
		SUBJECTNAME.add(SubjectName);
	}

	public static void addGRADE(String gRADE) {
		GRADE.add(gRADE);
	}

	public static void setSEMESTER_PERCENTAGE(double sEMESTER_PERCENTAGE) {
		SEMESTER_PERCENTAGE = sEMESTER_PERCENTAGE;
	}

	public static void setOVERALL_PERCENTAGE(double oVERALL_PERCENTAGE) {
		OVERALL_PERCENTAGE = oVERALL_PERCENTAGE;
	}
}
