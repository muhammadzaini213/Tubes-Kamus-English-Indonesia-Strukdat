# 📘 KamusGimmick — JavaFX Desktop Application

KamusGimmick is a cross-platform JavaFX desktop application built using **Java 21**, **OpenJFX 21**, and **Maven**.  
Supports **Linux** and **Windows** out of the box.

---

## 🚀 Features
- Modern **JavaFX 21** UI
- Uses **Commons Lang3**
- Linux run helper scripts
- Fully cross-platform
- Clean Maven setup

---

## 🛠 Requirements

### **Java 21**
You must have **JDK 21** installed.

#### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-21-jdk
````

#### Windows

Download OpenJDK 21 (Microsoft Build):

```
https://learn.microsoft.com/en-us/java/openjdk/download
```

### Verify installation:

```bash
java -version
javac -version
```

Expected:

```
openjdk version "21.x"
javac 21.x
```

---

## 🔧 Set JAVA_HOME

### Linux

```bash
readlink -f $(which java)

# Example result:
# /usr/lib/jvm/java-21-openjdk-amd64

export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
```

To make it permanent:

```bash
echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' >> ~/.bashrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.bashrc
source ~/.bashrc
```

### Windows

1. Open **System Properties → Environment Variables**
2. Add:

```
JAVA_HOME = C:\Program Files\Microsoft\jdk-21
```

3. Add to PATH:

```
%JAVA_HOME%\bin
```

---

## 📦 Install Maven

### Linux

```bash
sudo apt install maven
```

### Windows

Download:

```
https://maven.apache.org/download.cgi
```

Add **MAVEN_HOME** and `%MAVEN_HOME%\bin` to PATH.

### Verify

```bash
mvn -v
```

---

## ⚙️ Build the Project

Run:

```bash
mvn clean compile
```

Full build:

```bash
mvn clean install
```

---

## ▶️ Run the Application

Start the JavaFX app:

```bash
mvn javafx:run
```

---

## 🐧 Linux Helper Scripts

Two scripts are included to make running easier.

### Make executable:

```bash
chmod +x ./run.sh
chmod +x ./runcompile.sh
```

### Run application:

```bash
./run.sh
```

### Compile only:

```bash
./runcompile.sh
```

If you created the scripts on Windows, convert line endings:

```bash
sudo apt install dos2unix
dos2unix run.sh
dos2unix runcompile.sh
```

## 🧪 Troubleshooting

### JavaFX modules not found:

```bash
mvn dependency:resolve
```

### Permission denied (Linux):

```bash
chmod +x *.sh
```

### Maven central unreachable:

```bash
mvn -U clean install
```
