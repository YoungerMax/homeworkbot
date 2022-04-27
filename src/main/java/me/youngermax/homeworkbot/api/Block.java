package me.youngermax.homeworkbot.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public enum Block {
    // core classes //
    A("A"),
    B("B"),
    C("C"),
    D("D"),

    // electives //
    // 5
    E_5A("5A"),
    E_5B("5B"),
    E_5AB("5A-B"),
    // 6
    E_6A("6A"),
    E_6B("6B"),
    E_6AB("6A-B"),
    // 7
    E_7A("7A"),
    E_7B("7B"),
    E_7AB("7A-B");
    
    @Getter
    private final String normalName;

    public static Block[] getBlocksFromString(String blocksString) {
        List<Block> blocks = new ArrayList<>();

        blocksString = blocksString.replaceAll(" ", "");
        for (String block : blocksString.split("/")) {
            blocks.add(Block.valueOf(block));
        }

        return blocks.toArray(new Block[0]);
    }
}
