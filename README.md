# Animal Olympics Simulation

A Java-based simulation of an Olympic competition for various animal species. This project was developed by Rahamim Tadela (208189621) and Almog Ben Gur (207896697).

## Project Overview

This application simulates a competitive environment where different animal species can participate in Olympic-style races. The animals are divided into different categories based on their natural habitats and characteristics:

- Air Animals (Eagles, Pigeons)
- Terrestrial Animals (Dogs, Cats, Snakes)
- Water Animals (Dolphins, Alligators)

## Features

- Multiple animal species with unique characteristics and behaviors
- Thread-based racing simulation
- Energy management system for animals
- Visual representation of animals and races using Java Swing
- Category-specific attributes and capabilities for different animal types

## Class Structure

### Core Classes

- `Animal`: Abstract base class for all animals
- `AirAnimal`, `TerrestrialAnimals`, `WaterAnimal`: Category-specific abstract classes
- Animal implementations (e.g., `Dog`, `Cat`, `Eagle`, `Dolphin`, etc.)
- `AnimalThread`: Handles the racing logic for each animal

### Supporting Components

- `Sound`: Interface for animal sounds
- `IReptile`: Interface for reptile-specific behaviors
- `Mobile`: Base class for movement mechanics
- Graphics components for visual representation

## Implementation Details

- Animals have specific attributes like weight, speed, gender, and energy levels
- Racing simulation takes into account energy consumption per meter
- Animals have unique sounds and appearances
- Thread synchronization ensures proper race execution and termination

## System Requirements

- Java Runtime Environment (JRE) 8 or higher
- Java Swing support for graphical interface

## Running the Application

1. Compile the Java files
2. Run the main application entry point in the `System.Sys` class

## Contributors

- Rahamim Tadela
- Almog Ben Gur
