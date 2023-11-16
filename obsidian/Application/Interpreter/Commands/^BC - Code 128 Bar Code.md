 
[![Code 128 FAQ and General Specifications](https://www.precisionid.com/wp-content/uploads/Code128_Example.gif)

Mod 10 Check Digit: Documentation Page 384

## Details
***
**Description**
The `^BC` command creates the Code 128 bar code, a high-density, variable length, continuous, alphanumeric symbology. It was designed for complexly encoded product identification.

Code 128 has three subsets of characters. There are 106 encoded printing characters in each
set, and each character can have up to three different meanings, depending on the character
subset being used. Each Code 128 character consists of six elements: three bars and three
spaces.

• `^BC` supports a fixed print ratio.
• Field data (`^FD`) is limited to the width (or length, if rotated) of the label.

**Format:** `^BC o, h, f, g, e, m`

## Modes

#### 1. No Mode
***
**Character** - `N`
- Subsets are specified explicitly as part of the field data.

*This example shows with application identifier* `00` *structure*

```
^XA

^FO 90, 200 
^BY 4
^BC N, 256, Y, N, Y, N
^FD>;>80012345123451234512
```

- `>;>8` sets it to subset C, function 1
- `00` is the application identifier followed by 17 characters, the check digit is selected using the `Y` for the (e) parameter to automatically print the 20th character
- you are not limited to 19 characters with mode set to `N`

**Representation:**
![[N_for_the_m_parameter.png]]
#### 2. UCC  Case Mode
***
**Character:**  `U`
- More than 19 digits in `^FD` or `^SN` are eliminated.
- Fewer than 19 digits in `^FD` or `^SN` are filled with zeros to the right to bring the count to 19. This produces an invalid interpretation line.

*The example shows the application identifier* `00`  *format*
```
^XA

^FO 90, 200
^BY 4
^BC N, 256, Y, N, N, U
^FD 0012345123451234512 ^FS

^XZ
```

- Choosing U selects UCC Case mode. You will have exactly 19 characters available in `^FD`.
- Subset C using FNC1 values are automatically selected.
- Check digit is automatically inserted.

**Representation:**
![[U_for_the_m_parameter.png]]
#### 3. Automatic Mode
***
**Character:** `A`
- The subset is automatically determined by the program.

#### 4. UCC / EAN Mode
***
**Character:** `D`
- Dealing with UCC/EAN with and without chained application identifiers.
- The code starts in the appropriate subset followed by FNC1 to indicate a UCC/EAN 128 bar code.
- Automatically strips out parentheses '`(`' & '`)`' and spaces '` `' for encoding, but prints them in the human-readable section. 
- Automatically determines if a check digit is required, calculate it, and display it.
- Automatically sizes the human readable text.

*This example shows application identifier 00 format:*
```
^XA

^FO50,200^BCN,150,Y,N,,D
^FD(00)10084423 7449200940^FS

^XZ
```

(0 at end of field is a bogus character that is inserted as a place holder for the check digit this printer will automatically insert)
- Subset C using FNC1 values are automatically selected.
- Parentheses and spaces can be in the field data. '`00`' application identifier, followed by 17 characters, followed by bogus check digit place holder.
- Check digit is automatically inserted. The printer will automatically calculate the check digit and put it into the bar code and interpretation line.
- The interpretation line will also show the parentheses and spaces but will strip them out from the actual bar code.

**Representation:**
![[D_for_the_m_parameter.png]]
#### Printing the interpretation line
***
This example shows printing the interpretation in a different font

```
^XA

^FO 50, 200
^A 0N, 40, 30 
^BC N, 150, Y, N, Y
^FD >;>80012345123451234512 ^FS

^XZ
```

The font command `^A` can be added and changed to alter the font and size of the interpretation line.
# Subsets
***

|Invocation Code|Decimal Value |Subset A|Subset B |Subset C|
|---|---|---|---|---|
|><|62|||
|>0|30|>|>|
|>=|94||~|
|>1|95|USQ|DEL|
|>2|96|FNC 3|FNC 3|
|>3|97|FNC 2|FNC 2|
|>4|98|SHIFT|SHIFT|
|>5|99|CODE C|CODE C|
|>6|100|CODE B|FNC 4|CODE B
|>7|101|FNC 4|CODE A|CODE A
|>8|102|FNC 1|FNC 1|FNC 1

|Start Characters|Decimal Value| | |
|---|---|---|---|
|>9|103|Start Code A|Numeric Pairs give Alpha/Numerics|
|>:|104|Start Code B|Normal Alpha/Numeric|
|>;|105|Start Code C|All numeric (00-99)|

#### Subset B with no start character
***
**Code: 
```
^XA

^FO 100, 75
^BC N, 100, Y, N, N 
^FD CODE128 ^FS

^XZ
```

**Representation:**
![[Subset_B_with_no_start_character.png]]

#### Subset B with start character
***
**Code: 
```
^XA

^FO 100, 75
^BC N, 100, Y, N, N 
^FD >:CODE128 ^FS

^XZ
```

**Representation:**
![[Subset_B_with_start_character.png]]

#### Switching from Subset C to B to A
***
**Code:**
```
^XA
^FO 50, 50
^BY 3
^BC N, 100, Y, N, N
^FD >;382436>6CODE128>752375152 ^FS
^XZ
```

**Representation:**
![[Switching_from_Subset_C_to_B_to_A.png]]


#### Subset C with normal data
***
**Code:**
```
^XA
^FO 50, 50
^BC N, 100, Y, N, N
^FD >;382436 ^FS
^XZ
```

**Representation:**
![[Subset_C_with_normal_data.png]]

#### Subset C with ignored alpha character
***
**Code:**
```
^XA
^FO 50, 50
^BC N, 100, Y, N, N
^FD >;38D2436 ^FS
^XZ
```

**Representation:**
![[Subset_C_with_ignored_alpha_character.png]]

#### Subset A
***
**Code:**
```
^XA
^FO 50, 50
^BC N, 100, Y, N, N
^FD >;38D2436 ^FS
^XZ
```

**Representation: 
![[Subset_A.png]]