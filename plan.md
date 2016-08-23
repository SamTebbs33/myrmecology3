# Ant species
* Each species has certain traits and behaviours
* Their ant hills can be found in various biomes, depending on the characteristics of the species

# Capturing ants
* Ants will spawn in ant hills (there is a distinct type of hill for each species)
* Ants can be captured from hills by using an "Ant Extractor"

# Breeding
* Drones and queens of the same species are bred in a breeding chamber
* Produces a larva of that species with a random behaviour (determined from behaviour tree) from a set of behaviours that match the species

# Behaviour tree
(example)

Resource gathering
  - Wood
    - Chopping
    - Planting
  - Food
    - Crops
      - Planting
      - Harvesting
    - Animal
      - Slaughter
      - Breeding

A species given the "Resource gathering" tree could be given any of the above behaviours at random, but a species given the "Food" tree could only be given a behaviour from the "Crops" or "Animal" tree.
