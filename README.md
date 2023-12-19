ZPLViewer is a lightweight and versatile Java library for viewing Zebra Programming Language (ZPL) files. ZPL is a printer control language used by Zebra printers for label printing. This viewer allows developers and users to preview and inspect ZPL files before sending them to Zebra printers, aiding in the debugging and optimization of label designs.
This is project is abondand and will no longer be maintained, I put it up here for someone else as a starting point or to contribute.

## Features:
 - Parsing:      Seamlessly extract content from ZPL file.
 - Interpreter:  Transform parsed ZPL content into a vibrant and dynamic PDF file
 - PDF Display:  View the transformed PDF right in your browser

## Usage
 ` $ java -jar zplviewer_0.4.1.jar <input_file> <output_directory> <range>`<br>
   -> if you have multiple labels contained in your file you can specify the output pdf's.<br><br>
      Here is the syntax to do this:<br>
      - Single `{Value}` eg.: 1 -> output_1.pdf<br>
      - Range `{Start}-{End}`  eg.: 3-6 -> output_3-6.pdf<br>
      - Multiple `{Value}, {Range_1}, {Range_2}` eg.: 1, 3-6, 4-8 -> output_1.pdf, output_3-6.pdf, output_4-8.pdf

## Commands
| Command | Name                                | Ready | 
|---------|-------------------------------------|-------|
| `^A`    | Scalable/Bitmapped Font              | ✅    | 
| `^FW`   | Field Orientation                   | ❌    | 
| `^LH`   | Label Home                          | ❌    | 
| `^GF`   | Graphic Field                       | ❌    | 
| `^PO`   | Print Orientation                   | ❌    | 
| `^CC` `~CC`| Change Carets                    | ✅    | 
| `^LR`   | Label Reverse Print                 | ❌    | 
| `^MU`   | Set Units of Measurement            | ❌    | 
| `^LL`   | Label Length                        | ❌    | 
| `^PQ`   | Serialization Data           | ❌    | 
| `^PQ`   | Print Quantity                      | ❌    | 
| `^CI`   | Change International Font/Encoding   | ❌    | 
| `^CT` `~CT`| Change Tilde                      | ❌    | 
| `^BY`   | Bar Code Field Default               | ✅    | 
| `^BC`   | Code 128 Bar Code                   | 👷    | 
| `^CF`   | Change Alphanumeric Default Font     | ✅    | 
| `^FD`   | Field Data                          | ✅    | 
| `^FO`   | Field Origin                        | ✅    | 
| `^FR`   | Field Reverse Print                  | ✅    | 
| `^FS`   | Field Stop                          | ✅    | 
| `^FT`   | Field Typeset                       | ✅    | 
| `^GB`   | Graphic Box                         | ✅    | 
| `^XA`   | Start Format                        | ✅    | 
| `^XG`   | Recall Graphic                      | ✅    | 
| `^XZ`   | End Format                          | ✅    | 
| `~DG`   | Download Graphics                    | ✅    | 

You find more information on Code 128 Bar Code`^BC` in "obsidian/Application/Interpreter/Commands/^BC - Code 128 Bar Code" 

## Patch notes - 0.4.1 
- **Parser Enhancement:**
  - Adjusted the parser to facilitate obtaining the line and start index of a command.

- **Image Transformation:**
  - Added support for converting JPG, PNG, etc., into ZPL code.

- **Enhanced Barcode Display:**
  - Barcodes now include a descriptive text displayed below.

- **Extended Code 128 Barcode Functionality:**
  - Users can now utilize subsets A, B, and C in the Code 128 Barcode.

- **PDF Renderer Overhaul:**
  - Comprehensive redesign of the PDF renderer; the program now features a complete list of all elements.

- **Text Label Reverse:**
  - Introducing Label Reverse ('^LR') functionality for text.

- **Label Range Specification:**
  - Implemented the ability to specify ranges of labels within a ZPL document, enabling users to save them to a PDF file.

  
## Contributing:

I welcome contributions! If you have ideas for improvements or bug fixes, please submit a pull request.

## Acknowledgments:

Special thanks to contributors and the open-source community for their support.

Feel free to use, modify, and contribute to ZPLViewer to enhance the ZPL label printing experience in your Java applications.
