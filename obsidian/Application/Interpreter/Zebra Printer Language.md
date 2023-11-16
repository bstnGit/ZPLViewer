
## Spezifikationen
***
#### Label
- Ist in Feldern organisiert

#### Command
- Beginnen mit ^ oder ~
- Namen sind 1 - 2 lang und beachten keine Groß- und Kleinschreibung

#### DPI
The units of the lable's coordinate system is in dots the smallest unit a printer can handle

**Commonly used:** 

- 8 dpmm (8 dots per millimeter)
- 6 dpmm(150 dpi )
- 12 dpmm(300 dpi)
- 24 dpmm (600 dpi)

## Command Liste
***

#### General

| Command | Parameter | Beschreibung |
| --- | --- | --- |
|`^XA`||start of the label|
|`^XZ`||end of the label |
|`^FS`||finishes a field and starts a new one|
|`^FO`|`<x>`: numeric `<y>`: numeric|defines the field origin|

**Example**:

```ZPL
^XA

^FO 100, 200 ^FS

^XZ
```

#### Draw Text

| Command | Parameter | Beschreibung |
| --- | --- | --- |
|`^FD`|`<value>`|defines the fields data -> Whitespaces are significant|
|`^A`|`<name>`: numeric, char `height`: numeric `width`: numeric|defines a font with name to specify wich font is used, font height and font width|

**Example:** `^FO 50, 60 ^A 0, 40 ^FD World's Best Griddle ^FS`

#### Draw Barcode

| Command | Parameter | Beschreibung |
| --- | --- | --- |
|`^BY`|`<width>`: numeric `<height>`: numeric| adjusts barcode module width and, optionally, barcode height in dots. |
|`^FD`|`<value>`|The allocated value is encoded by the specified Barcode type|
| `^BC`     | `orientation`: numeric                      | Orientation of Code 128 barcode               |
|         | `height`: numeric                           | Height of the barcode                         |
|         | `showHumanReadableText`: string              | Display human-readable text                   |
|         | `showTextAbove`: string                     | Show text above the barcode                   |
|         | `addUccCheckDigit`: string                  | Add UCC check digit                            |
|         | `mode`: string                              | Barcode mode                                  |
| `^BO`     | `orientation`: numeric                      | Orientation of Aztec barcode                  |
|         | `magnification`: numeric                    | Magnification factor                          |
|         | `eci`: numeric                             | Extended Channel Interpretation (ECI)         |
|         | `size`: numeric                            | Symbol size (square)                         |
|         | `readerInit`: numeric                      | Reader initialization                         |
|         | `structuredAppendCount`: numeric           | Number of structured append barcodes          |
|         | `structuredAppendMessageId`: numeric       | ID for structured append messages              |
| `^BQ`     | `orientation`: numeric                      | Orientation of QR code                         |
|         | `model`: string                            | QR code model                                 |
|         | `magnification`: numeric                    | Magnification factor                          |
|         | `errorCorrection`: string                   | Error correction level                        |
|         | `mask`: numeric                            | QR code mask pattern                          |
| `^BX`     | `orientation`: numeric                      | Orientation of Data Matrix code               |
|         | `height`: numeric                           | Height of the barcode                         |
|         | `qualityLevel`: numeric                     | Print quality level                           |
|         | `columns`: numeric                          | Number of data columns                        |
|         | `rows`: numeric                             | Number of data rows                           |
|         | `format`: string                           | Data format (ASCII, C40, Text, etc.)          |
|         | `escape`: string                           | Escape character usage                        |
|         | `aspectRatio`: numeric                     | Aspect ratio of the barcode                   |
| `^B3`     | `orientation`: numeric                      | Orientation of Code 39 barcode                |
|         | `addCheckDigit`: string                    | Add a check digit                             |
|         | `height`: numeric                           | Height of the barcode                         |
|         | `showHumanReadableText`: string              | Display human-readable text                   |
|         | `showTextAbove`: string                     | Show text above the barcode                   |

**Example:** `^FO 60, 120 ^BY 3 ^ BCN ,60,,,, FD^FD1234ABC ^FS`


#### Draw Shapes

| Command | Parameter | Beschreibung |
| --- | --- | --- |
|`^GD`|`width`: numeric `height`: numeric `thickness`: numeric `color`: B/W `orientation`: L/R|Graphic Diagonal to draw line|
|`^GB`|`width`: numeric `height`: numeric `thickness`: numeric `color`: B/W `rounding`: numeric 0-8| Graphic Box to draw rectangle| 
|`^GC`|`diameter`: numeric `thickness`: numeric `color`: B/W| Graphic Circle to draw circle|
|`^GE`|`width`: numeric `height`: numeric `thickness`: numeric, `color`: B/W `rounding`: numeric|Graphic Ellipses to draw ellipses|

**Example:** `^FO 25, 25 ^GB 380, 200, 2 ^FS`



#### Draw Images
| Command | Parameter | Beschreibung |
| --- | --- | --- |
|`^GF`| `format`: A/B/C `dataBytes`: numeric `totalBytes`: numeric `rowBytes`: numeric|Graphic Field to draw images|

**Note:** You can also upload images beforehand with `~DG` or `~DY` commands and then refer to these images by path using `^XG`




