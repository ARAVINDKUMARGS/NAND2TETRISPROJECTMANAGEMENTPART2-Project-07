# NAND2TETRIS – Project 7  
## Virtual Machine I: Stack Arithmetic

---

## Course Information
**Course:** Build a Modern Computer from First Principles (Nand2Tetris – Part II)  
**Institution:** Hebrew University of Jerusalem  
**Project Code:** PROJECT-07  

---

## Project Overview

Project 7 implements the **first stage of a Virtual Machine (VM) Translator**.  
The translator converts programs written in the **VM language** into **Hack assembly code**, enabling VM programs to run on the Hack computer.

This project focuses on **stack-based arithmetic and memory access commands**, forming the foundation for the full VM translator completed in Project 8.

---

## Objectives

- Parse `.vm` files containing VM commands
- Translate VM commands into equivalent Hack assembly code
- Implement stack arithmetic and logical operations
- Implement memory access commands (`push` and `pop`)
- Generate correct `.asm` output compatible with the Hack CPU Emulator

---

## VM Commands Supported

### Arithmetic / Logical Commands
- `add`
- `sub`
- `neg`
- `eq`
- `gt`
- `lt`
- `and`
- `or`
- `not`

### Memory Access Commands
- `push segment index`
- `pop segment index`

### Supported Memory Segments
- `constant`
- `local`
- `argument`
- `this`
- `that`
- `temp`
- `pointer`
- `static`

---

## Folder Structure

```

Project-07/
│── README.md
│── src/
│   └── VMTranslator.java
│── tests/
│   ├── SimpleAdd.vm
│   ├── StackTest.vm
│   ├── BasicTest.vm
│   ├── PointerTest.vm
│   └── StaticTest.vm
│── output/
│   └── *.asm

````

---

## Usage

### Compile the Translator
```bash
javac VMTranslator.java
````

### Run the Translator

```bash
java VMTranslator path/to/File.vm
```

* Generates a `.asm` file in the same directory as the input `.vm` file
* Output file name matches the input file name

---

## Testing

The translator is tested using **official Nand2Tetris test programs**:

### Arithmetic Tests

* `SimpleAdd.vm`
* `StackTest.vm`

### Memory Access Tests

* `BasicTest.vm`
* `PointerTest.vm`
* `StaticTest.vm`

### Testing Procedure

1. Run `.vm` file using the **VM Emulator**
2. Translate `.vm` → `.asm`
3. Run `.asm` using the **CPU Emulator**
4. Validate output using `.tst` and `.cmp` files

---

## Notes

* No bootstrap code is required in Project 7
* Stack initialization and memory mapping are handled by test scripts
* Output must contain **only translated VM commands**
* This project is a prerequisite for **Project 8 (VM II – Program Control)**

---

## Author

**Aravind Kumar GS**
Email: [aravindkumar06062006@gmail.com](mailto:aravindkumar06062006@gmail.com)

---

## License


This project is part of the Nand2Tetris course and is intended for **educational purposes only**.
