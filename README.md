# NAND2TETRISPROJECTMANAGEMENTPART2-Project-07
PROJECT-07
# NAND2TETRISPROJECTMANAGEMENTPART2-Project-7

## Project 7 – Jack Compiler Part I

**Project Code:** PROJECT07
**Course:** Build a Modern Computer from First Principles (Nand2Tetris Part II)
**Institution:** Hebrew University of Jerusalem

---

## Overview

Project 7 implements the **first phase of the Jack Compiler**, focusing on **lexical analysis (tokenization) and parsing**. The compiler reads `.jack` source files and produces **XML parse trees**. These parse trees are used in **Project 8** to generate VM code.

This project lays the foundation for building a **full compiler** capable of translating Jack programs into VM code executable on the Hack platform.

---

## Objectives

* Implement a **tokenizer** to break Jack code into valid tokens.
* Implement a **parser** to analyze syntax and generate parse trees.
* Handle **classes, methods, variables, and statements** in the Jack language.
* Produce XML parse trees suitable for further compilation.

---

## Folder Structure

```
Project7/
│── README.md
│── src/
│   └── JackCompiler.java
│── examples/
│   └── Main.jack
│── output/
│   └── Main.xml
│── docs/
│   └── CompilerI_Guide.pdf
```

---

## Getting Started

### Step 1: Compile the Compiler

```bash
cd Project7/src
javac JackCompiler.java
```

### Step 2: Run the Compiler on a Jack File

```bash
java JackCompiler ../examples/Main.jack
```

### Step 3: Check Output

* The compiler generates `Main.xml` in the `output/` folder.
* Open the XML parse tree in any XML viewer or IDE to verify correct structure.

---

## Supported Features

### Tokenization

* Keywords: `class`, `method`, `function`, `constructor`, `int`, `boolean`, `char`, etc.
* Symbols: `{ } ( ) [ ] . , ; + - * / & | < > = ~`
* Identifiers, integer constants, string constants

### Parsing

* Class declarations
* Method and function definitions
* Statements: `let`, `if`, `while`, `do`, `return`
* Expressions and terms

### Example

**Input Jack code (`Main.jack`):**

```jack
class Main {
   function void main() {
      do Output.printString("Hello, world");
      return;
   }
}
```

**Output XML (`Main.xml`):**

```xml
<class>
  <keyword>class</keyword>
  <identifier>Main</identifier>
  ...
</class>
```

---

## Notes

* This project **does not generate VM code**; it only creates parse trees.
* Correct parse trees are essential for Project 8 (VM code generation).
* Handles syntax errors gracefully with descriptive messages.

---

## Author

**Aravind Kumar GS**
Email: `aravindkumar06062006@gmail.com`

---

## License

Educational purposes only. Do not distribute or claim as your own work.
