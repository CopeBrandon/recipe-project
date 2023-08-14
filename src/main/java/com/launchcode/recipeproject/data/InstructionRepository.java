package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.Instruction;
import org.springframework.data.repository.CrudRepository;

public interface InstructionRepository extends CrudRepository<Instruction, Integer> {
}
