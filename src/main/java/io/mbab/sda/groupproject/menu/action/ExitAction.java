package io.mbab.sda.groupproject.menu.action;

public class ExitAction implements MenuAction{
    @Override
    public void execute() {
        System.exit(0);
    }
}