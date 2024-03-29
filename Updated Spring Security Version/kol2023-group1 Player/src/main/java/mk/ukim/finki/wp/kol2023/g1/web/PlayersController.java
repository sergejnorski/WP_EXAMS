package mk.ukim.finki.wp.kol2023.g1.web;

import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;

public class PlayersController {

    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * This method should use the "list.html" template to display all players.
     * The method should be mapped on paths '/' and '/players'.
     * The arguments that this method takes are optional and can be 'null'.
     * In the case when the arguments are not passed (both are 'null') all players should be displayed.
     * If one, or both of the arguments are not 'null', the players that are the result of the call
     * to the method 'listPlayersWithPointsLessThanAndPosition' from the PlayerService should be displayed.
     *
     * @param pointsPerGame
     * @param position
     * @return The view "list.html".
     */
    public String showPlayers(Double pointsPerGame, PlayerPosition position) {
        if (pointsPerGame == null && position == null) {
            this.playerService.listAllPlayers();
        } else {
            this.playerService.listPlayersWithPointsLessThanAndPosition(pointsPerGame, position);
        }
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/players/add'.
     *
     * @return The view "form.html".
     */
    public String showAdd() {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the player that is updated.
     * The method should be mapped on path '/players/[id]/edit'.
     *
     * @return The view "form.html".
     */
    public String showEdit(Long id) {
        this.playerService.findById(id);
        return "";
    }

    /**
     * This method should create a player given the arguments it takes.
     * The method should be mapped on path '/players'.
     * After the player is created, all players should be displayed.
     *
     * @return The view "list.html".
     */
    public String create(String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        this.playerService.create(name, bio, pointsPerGame, position, team);
        return "";
    }

    /**
     * This method should update a player given the arguments it takes.
     * The method should be mapped on path '/players/[id]'.
     * After the player is updated, all players should be displayed.
     *
     * @return The view "list.html".
     */
    public String update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        this.playerService.update(id, name, bio, pointsPerGame, position, team);
        return "";
    }

    /**
     * This method should delete the player that has the appropriate identifier.
     * The method should be mapped on path '/players/[id]/delete'.
     * After the player is deleted, all players should be displayed.
     *
     * @return The view "list.html".
     */
    public String delete(Long id) {
        this.playerService.delete(id);
        return "";
    }

    /**
     * This method should increase the number of votes of the appropriate player by 1.
     * The method should be mapped on path '/players/[id]/vote'.
     * After the operation, all players should be displayed.
     *
     * @return The view "list.html".
     */
    public String vote(Long id) {
        this.playerService.vote(id);
        return "";
    }
}
