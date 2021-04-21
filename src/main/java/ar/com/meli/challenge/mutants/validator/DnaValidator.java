package ar.com.meli.challenge.mutants.validator;

import ar.com.meli.challenge.mutants.exceptions.BadFormatDnaException;
import ar.com.meli.challenge.mutants.exceptions.DnaSizeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DnaValidator {

    private DnaValidator() {
        throw new IllegalStateException();
    }

    public static void validateDna(String [] dna) throws BadFormatDnaException, DnaSizeException {
        log.info("Start matrix checking");
        int rowsLength = dna.length;
        for (String row: dna) {
            checkDnaComposition(row);
            checkRowWithMatrix(row.length(), rowsLength);
        }
    }

    private static void checkRowWithMatrix(int colLength, int rowsLength) throws DnaSizeException {
        if(colLength != rowsLength){
            log.info("The size of the matrix are incorrect. Check the length of the columns and rows are equals. [NxN]");
            throw new DnaSizeException("The size of the matrix are incorrect. Check the length of the columns and rows are equals. [NxN]");
        }
    }

    private static void checkDnaComposition(String row) throws BadFormatDnaException {
        if(!row.matches("^[ATCG]+$")){
            log.info("The dna contains a bad letter to represent a nitrogen base.");
            throw new BadFormatDnaException("The dna contains a bad letter to represent a nitrogen base.");
        }
    }

}
