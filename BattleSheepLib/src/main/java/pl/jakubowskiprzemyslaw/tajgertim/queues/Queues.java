package pl.jakubowskiprzemyslaw.tajgertim.queues;

public enum Queues {
    _1PlayerRegistrationQueue("PlayerRegistrationQueue"),
    _2GameConfigurationRegistrationQueue("GameConfigurationRegistrationQueue"),
    _3FleetPlacementSizeQueue("FleetPlacementSizeQueue"),
    _4FleetPlacementQueue("FleetPlacementQueue"),
    _5GameReadyValidationQueue("GameReadyValidationQueue"),
    _6BoardHandlerPlayerQueue("BoardHandlerPlayerQueue"),
    _7BoardHandlerFleetPlacementQueue("BoardHandlerFleetPlacementQueue"),
    _8JudgeStartQueue("JudgeStartQueue"),
    _9PlayingStateMachinePlayerActionQueue("PlayingStateMachinePlayerActionQueue"),
    _10ShotHandlerPlayerShotQueue("ShotHandlerPlayerShotQueue"),
    _11MoveHandlerPlayerMoveQueue("MoveHandlerPlayerMoveQueue"),
    _12BoardHandlerShotQueryQueue("BoardHandlerShotQueryQueue"),
    _13BoardHandlerMoveQueryQueue("BoardHandlerMoveQueryQueue"),
    _14PlayingStateMachineNextRoundStatusQueue("PlayingStateMachineNextRoundStatusQueue"),
    _15JudgePlayerShootResultQueue("JudgePlayerShootResultQueue"),
    _16JudgePlayerMoveResult("JudgePlayerMoveResultQueue"),
    _17ShotHandlerFieldStatusQueue("ShotHandlerFieldStatusQueue"),
    _18PlayingBoardsViewQueue("PlayingBoardsViewQueue"),
    _19PlayingPlayerQueue("PlayingPlayerQueue"),
    _20PlayingStateMachineOpponentsQueue("PlayingStateMachineOpponentsQueue");

    private final String name;

    Queues(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
