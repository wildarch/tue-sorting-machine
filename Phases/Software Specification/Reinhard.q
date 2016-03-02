//This file was generated from (Commercial) UPPAAL 4.0.14 (rev. 5615), May 2014

/*

*/
A[] ProcessReinhard.INIT imply not machinePaused == 1

/*
It is possible that the system goes to the ABORT state
*/
E<> ProcessReinhard.ABORT

/*
In the initial and paused states all peripherals are in their idle state
*/
A[] (ProcessReinhard.paused or ProcessReinhard.peripheral_reset) imply (ProcessMotor.INIT and ProcessGyroSensor.INIT)

/*
The gyro is never checked in full speed mode
*/
A[] mode == 0 imply ProcessGyroSensor.INIT

/*
the sorting machine can finish sorting
*/
E<> ProcessReinhard.DONE

/*
When not in incremental mode, machinePaused is always 1 in the paused state
*/
A[] ((ProcessReinhard.paused and mode != 2) imply machinePaused==1)

/*

*/
A[] mode==0 or mode==1 or mode==2

/*
The system never deadlocks
*/
A[] not deadlock
