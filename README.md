# AI-Based University Course Scheduling Using Genetic Algorithm

An AI-based university course scheduling system developed for COMP338 – Artificial Intelligence.

The project uses a Genetic Algorithm (GA) to automatically generate university course timetables while satisfying hard constraints and minimizing soft constraint violations.

## Project Overview

University course scheduling is a complex optimization problem because courses must be assigned to available time slots and rooms while considering instructors, student groups, room capacities, room types, and instructor availability.

In this project, each candidate timetable is represented as a chromosome. Each chromosome contains one gene for every course, and each gene represents a course assignment:

`Gene = (Course, Day, Time, Room)`

The Genetic Algorithm improves candidate timetables through selection, crossover, mutation, room repair, and elitism.

## Hard Constraints

The implementation evaluates six hard constraints:

- **H1 – Instructor Conflict:** An instructor cannot teach two courses at the same day and time.
- **H2 – Room Conflict:** A room cannot be assigned to two courses at the same day and time.
- **H3 – Student Conflict:** Courses attended by the same student group cannot overlap.
- **H4 – Room Capacity:** The assigned room must have sufficient capacity.
- **H5 – Room Type:** The room type must match the course type.
- **H6 – Instructor Availability:** Courses cannot be scheduled when the instructor is unavailable.

## Soft Constraints

The implementation evaluates five soft constraints:

- **S1 – Avoid Early Slots:** Penalizes courses scheduled at 08:00–09:00.
- **S2 – Avoid Late Slots:** Penalizes courses scheduled at 11:00–12:00.
- **S3 – Minimize Student Gaps:** Penalizes undesirable gaps between courses of the same student group.
- **S4 – Instructor Preferences:** Penalizes assignments that do not follow defined instructor preferences.
- **S5 – Minimize Working Days:** Penalizes schedules that spread a student group across all five working days.

## Genetic Algorithm

The implemented Genetic Algorithm follows this process:

`Initial Population → Fitness Evaluation → Parent Selection → Crossover → Mutation → Room Repair → Elitism → New Population → Termination`

### Main Components

- Population Initialization
- Fitness Function
- Tournament Selection
- Two-Point Crossover
- Random Reassignment Mutation
- Room Repair
- Elitism
- Constraint Evaluation
- Early Stopping
- Convergence Analysis

## Fitness Function

The fitness function is based on hard and soft constraint violations.

```text
Penalty = 100H + 10S
Fitness = 1 / (1 + Penalty)
