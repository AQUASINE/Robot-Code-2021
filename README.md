# Robot-Code-2021

#### **How to run the code:** (for those who are new)

You'll want to use Git to import the project into IntelliJ or VSCode from Github. To do this, you'll need to have Git installed on your machine.

If you are looking at this in the future (after the 2021 season), you'll want to set up at first using VSCode, I recommend using the WPILib version that comes with the WPILib installer. This will make sure that you can download all features necessary and run WPILib commands, like changing vendor dependencies as well as creating the project for the season.

To make sure the code compiles, we use the command `gradlew build`. In some cases, you may have to run `./gradlew build` with the added `./`.

To actually download the code onto the robot, you must be connected to the robot via USB or Ethernet, and you must run the `gradlew deploy` command.


#### **How to run the unit tests:**

We have written some unit tests to make sure the commands function as expected. To run these in IntelliJ, simply right click on the project folder in the Project Pane and select `Run "Tests in Robot-Code-2021"`. If this does not work, you must go to Settings>Build, Execution, and Development> Build Tools> Gradle and change how you run your tests from Gradle to IntelliJ IDEA.

We are currently unsure how to make this work in VSCode.