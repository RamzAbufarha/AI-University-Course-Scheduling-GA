# AI-Based University Course Scheduling Using Genetic Algorithm

AI-based university course scheduling system developed for **COMP338 – Artificial Intelligence** using a Genetic Algorithm in Java.

## Features

- Genetic Algorithm optimization
- Tournament Selection
- Two-Point Crossover
- Random Reassignment Mutation
- Elitism and Room Repair
- Hard and Soft Constraint Evaluation
- Fitness and Convergence Analysis
- Parameter and Dataset Experiments

## Constraints

**Hard:** Instructor conflicts, room conflicts, student conflicts, room capacity, room type, instructor availability.

**Soft:** Early/late slots, student gaps, instructor preferences, and working days.

## Fitness

```text
Penalty = 100H + 10S
Fitness = 1 / (1 + Penalty)
```

## Baseline Result

| Metric | Result |
|---|---:|
| Courses | 10 / 10 |
| Hard Violations | 0 |
| Soft Violations | 0 |
| Fitness | 1.0 |
| Generations | 56 |

## Experiments

Population size, mutation rate, crossover rate, and dataset size were evaluated using fitness, constraint violations, generations, and runtime.

## Technologies

**Java · Genetic Algorithms · OOP · Constraint Optimization**


## Author

**Ramz Abufarha**
