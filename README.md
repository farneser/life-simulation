# Второе задание курса [java-backend-learning-course](https://zhukovsd.github.io/java-backend-learning-course/)

[![Java CI with Maven](https://github.com/farneser/life-simulation/actions/workflows/maven.yml/badge.svg)](https://github.com/farneser/life-simulation/actions/workflows/maven.yml)

## [Задание](https://zhukovsd.github.io/java-backend-learning-course/Projects/Simulation/)

Задание подразумевает разработку пошаговой симуляции 2D мира

## Правила игрового мира

* Каждый ход здоровье тратится на 1 единицу
* У животных есть скорость, показатель сколько клеток животное может пройти за один игровой цикл
* Когда травоядный находится вблизи травы, он ее ест, пополняя этим здоровье
* Когда хищник находится вблизи травоядного животного, он наносит ему урон
* Когда сущность находится рядом с едой, он может есть ее пока она не умер столько, сколько шагов у него осталось

## Предстоит реализовать

* размножение сущностей

## Параметры запуска

* "-s" - запуск в простом режиме (режим отображения сущностей в виде символов)
