package edu.birzeit.todo_list;

public class Task {
        private String description;
        private boolean completed;


    public Task(String description,boolean status) {
            this.description = description;
            status = false;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isCompleted() {
            return completed;
        }


    public void setCompleted(boolean completed) {
            this.completed = completed;
        }

    }
