package mk.ukim.finki.wp.jan2023.web;

import mk.ukim.finki.wp.jan2023.model.Gender;
import mk.ukim.finki.wp.jan2023.service.CandidateService;

import java.time.LocalDate;

public class CandidatesController {

    private final CandidateService candidateService;

    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    /**
     * This method should use the "list.html" template to display all candidates.
     * The method should be mapped on paths '/' and '/candidates'.
     * The arguments that this method takes are optional and can be 'null'.
     * In the case when the arguments are not passed (both are 'null') all candidates should be displayed.
     * If one, or both of the arguments are not 'null', the candidates that are the result of the call
     * to the method 'listCandidatesYearsMoreThanAndGender' from the CandidateService should be displayed.
     *
     * @param years
     * @param gender
     * @return The view "list.html".
     */
    public String showCandidates(Integer years, Gender gender) {
        if (years == null && gender == null) {
            this.candidateService.listAllCandidates();
        } else {
            this.candidateService.listCandidatesYearsMoreThanAndGender(years, gender);
        }
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/candidates/add'.
     *
     * @return The view "form.html".
     */
    public String showAdd() {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the candidate that is updated.
     * The method should be mapped on path '/candidates/[id]/edit'.
     *
     * @return The view "form.html".
     */
    public String showEdit(Long id) {
        this.candidateService.findById(id);
        return "";
    }

    /**
     * This method should create a candidate given the arguments it takes.
     * The method should be mapped on path '/candidates'.
     * After the candidate is created, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    public String create(String name, String bio, LocalDate dateOfBirth, Gender gender, Long party) {
        this.candidateService.create(name, bio, dateOfBirth, gender, party);
        return "";
    }

    /**
     * This method should update a candidate given the arguments it takes.
     * The method should be mapped on path '/candidates/[id]'.
     * After the candidate is updated, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    public String update(Long id, String name, String bio, LocalDate dateOfBirth, Gender gender, Long party) {
        this.candidateService.update(id, name, bio, dateOfBirth, gender, party);
        return "";
    }

    /**
     * This method should delete the candidate that has the appropriate identifier.
     * The method should be mapped on path '/candidates/[id]/delete'.
     * After the candidate is deleted, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    public String delete(Long id) {
        this.candidateService.delete(id);
        return "";
    }

    /**
     * This method should increase the number of votes of the appropriate candidate by 1.
     * The method should be mapped on path '/candidates/[id]/vote'.
     * After the operation, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    public String vote(Long id) {
        this.candidateService.vote(id);
        return "";
    }
}
