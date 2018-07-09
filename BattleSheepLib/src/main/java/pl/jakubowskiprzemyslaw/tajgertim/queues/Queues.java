package pl.jakubowskiprzemyslaw.tajgertim.queues;

public enum Queues {
    _1PlayerRegistrationQueue("PlayerRegistrationQueueTest"),
    _2GameConfigurationRegistrationQueue("GameConfigurationRegistrationQueueTest"),
    _3FleetPlacementSizeQueue("FleetPlacementSizeQueueTest"),
    _4FleetPlacementQueue("FleetPlacementQueueTest"),
    _5GameReadyValidationQueue("GameReadyValidationQueueTest"),
    _6BoardHandlerPlayerQueue("BoardHandlerPlayerQueueTest"),
    _7BoardHandlerFleetPlacementQueue("BoardHandlerFleetPlacementQueueTest"),
    _8JudgeStartQueue("JudgeStartQueueTest"),
    _9PlayingStateMachinePlayerActionQueue("PlayingStateMachinePlayerActionQueueTest"),
    _10ShotHandlerPlayerShotQueue("ShotHandlerPlayerShotQueueTest"),
    _11MoveHandlerPlayerMoveQueue("MoveHandlerPlayerMoveQueueTest"),
    _12BoardHandlerShotQueryQueue("BoardHandlerShotQueryQueueTest"),
    _13BoardHandlerMoveQueryQueue("BoardHandlerMoveQueryQueueTest"),
    _14PlayingStateMachineNextRoundStatusQueue("PlayingStateMachineNextRoundStatusQueueTest"),
    _15JudgePlayerShootResultQueue("JudgePlayerShootResultQueueTest"),
    _16JudgePlayerMoveResult("JudgePlayerMoveResultQueue"),
    _17ShotHandlerFieldStatusQueue("ShotHandlerFieldStatusQueueTest"),
    _18PlayerStateMachineBoardStatusQueue("PlayerStateMachineBoardStatusQueueTest");

    private final String name;

    Queues(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
