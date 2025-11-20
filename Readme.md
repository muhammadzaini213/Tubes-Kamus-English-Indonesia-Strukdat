<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>KamusGimmick — README</title>
  <style>
    :root{--bg:#0b1020;--card:#0f1724;--muted:#9aa4b2;--accent:#7dd3fc;--code-bg:#071026}
    body{font-family:Inter, ui-sans-serif, system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial; background:linear-gradient(180deg,#071026, #071632 140%); color:#e6eef6; margin:0; padding:2rem;}
    .wrap{max-width:980px;margin:0 auto;}
    header{display:flex;gap:16px;align-items:center;margin-bottom:1rem}
    header h1{font-size:1.6rem;margin:0}
    header p{margin:0;color:var(--muted)}
    .card{background:linear-gradient(180deg, rgba(255,255,255,0.02), rgba(255,255,255,0.01)); border:1px solid rgba(255,255,255,0.03); padding:1.25rem;border-radius:12px;box-shadow:0 6px 24px rgba(3,7,18,0.6); margin-bottom:1rem}
    h2{color:var(--accent); margin-top:1rem}
    pre{background:var(--code-bg); padding:0.9rem; border-radius:8px; overflow:auto; color:#d4f1ff; font-size:0.95rem; line-height:1.45}
    code{font-family:SFMono-Regular, Menlo, Monaco, "Roboto Mono", "Courier New", monospace}
    ul{line-height:1.6}
    .note{color:var(--muted); font-size:0.95rem}
    footer{color:var(--muted); font-size:0.9rem; margin-top:1.5rem}
    .kbd{background:#071a28;border:1px solid rgba(255,255,255,0.03);padding:2px 8px;border-radius:6px;font-family:monospace}
  </style>
</head>
<body>
  <div class="wrap">
    <header>
      <div>
        <h1>📘 KamusGimmick — JavaFX Project</h1>
        <p class="note">A JavaFX desktop application built with Java 21 and Maven — cross-platform (Linux &amp; Windows).</p>
      </div>
    </header>

    <section class="card">
      <h2>🚀 Features</h2>
      <ul>
        <li>JavaFX 21 UI</li>
        <li>Commons Lang utilities</li>
        <li>Cross-platform (Linux &amp; Windows)</li>
        <li>Helper run scripts for Linux</li>
      </ul>
    </section>

    <section class="card">
      <h2>🛠️ Requirements</h2>

      <h3>Java 21</h3>
      <p class="note">Install OpenJDK 21 (Linux &amp; Windows).</p>

      <strong>Linux (Ubuntu / Debian):</strong>
      <pre><code>sudo apt update
sudo apt install openjdk-21-jdk</code></pre>

      <strong>Windows:</strong>
      <p class="note">Download OpenJDK 21 (Microsoft Build or other distributor):</p>
      <pre><code>https://learn.microsoft.com/en-us/java/openjdk/download</code></pre>

      <h3>Verify Java</h3>
      <pre><code>java -version
javac -version</code></pre>
      <p class="note">Expected:</p>
      <pre><code>openjdk version "21.x"
javac 21.x</code></pre>

      <h3>Set <span class="kbd">JAVA_HOME</span></h3>
      <strong>Linux</strong>
      <pre><code>readlink -f $(which java)
# example path: /usr/lib/jvm/java-21-openjdk-amd64

# set temporarily (current shell)
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"

# set permanently for Bash
echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' >> ~/.bashrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.bashrc
source ~/.bashrc</code></pre>

      <strong>Windows</strong>
      <pre><code># Use System Properties → Environment Variables
# Set:
JAVA_HOME = C:\Program Files\Microsoft\jdk-21
# Add to PATH:
%JAVA_HOME%\bin</code></pre>

      <h3>Install Maven</h3>
      <strong>Linux:</strong>
      <pre><code>sudo apt install maven</code></pre>

      <strong>Windows:</strong>
      <pre><code>Download from: https://maven.apache.org/download.cgi
Unzip and add MAVEN_HOME and %MAVEN_HOME%\bin to PATH</code></pre>

      <p class="note">Verify:</p>
      <pre><code>mvn -v</code></pre>
    </section>

    <section class="card">
      <h2>⚙️ Build the Project</h2>
      <p>From the project root (where <code>pom.xml</code> lives):</p>
      <pre><code>mvn compile
mvn clean install</code></pre>
    </section>

    <section class="card">
      <h2>▶️ Run the Application</h2>
      <p>Use the JavaFX Maven plugin to run:</p>
      <pre><code>mvn javafx:run</code></pre>
    </section>

    <section class="card">
      <h2>🐧 Linux Run Scripts</h2>
      <p>Two helper scripts are provided:</p>

      <h4>Make scripts executable (only once):</h4>
      <pre><code>chmod +x ./run.sh
chmod +x ./runcompile.sh</code></pre>

      <h4>Run app (no need to type full Maven command):</h4>
      <pre><code>./run.sh</code></pre>

      <h4>Compile only (useful for CI or quick checks):</h4>
      <pre><code>./runcompile.sh</code></pre>

      <p class="note">If you created these scripts locally, ensure the first line (shebang) is:</p>
      <pre><code>#!/usr/bin/env bash</code></pre>
      <p class="note">If you see <em>bad interpreter: No such file or directory</em>, convert CRLF to LF:</p>
      <pre><code>sudo apt install dos2unix
dos2unix run.sh
dos2unix runcompile.sh</code></pre>
    </section>

    <section class="card">
      <h2>📁 Project Structure</h2>
      <pre><code>kamusgimmick/
 ├─ src/
 │   ├─ main/java/com/kamus/gimmick/
 │   ├─ main/resources/
 ├─ pom.xml
 ├─ run.sh
 ├─ runcompile.sh
 └─ README.md (or README.html)</code></pre>
    </section>

    <section class="card">
      <h2>🧪 Troubleshooting</h2>
      <ul>
        <li><strong>JavaFX modules not found:</strong>
          <pre><code>mvn dependency:resolve</code></pre>
        </li>
        <li><strong>Permission denied (Linux):</strong>
          <pre><code>chmod +x *.sh</code></pre>
        </li>
        <li><strong>Plugin metadata/network errors:</strong>
          <pre><code>mvn -U clean install</code></pre>
        </li>
        <li><strong>Maven central unreachable:</strong> check network/proxy or try again later.</li>
      </ul>
    </section>

    <section class="card">
      <h2>📝 Example run.sh</h2>
      <p>Save this as <code>run.sh</code> in the project root and make it executable.</p>
      <pre><code>#!/usr/bin/env bash
set -euo pipefail

# ensure JAVA_HOME is set (fallback to system java)
if [ -z "${JAVA_HOME:-}" ]; then
  echo "JAVA_HOME not set — attempting to continue with system java"
else
  export PATH="$JAVA_HOME/bin:$PATH"
fi

echo "Running KamusGimmick (mvn javafx:run)..."
mvn javafx:run</code></pre>

      <h2>📝 Example runcompile.sh</h2>
      <pre><code>#!/usr/bin/env bash
set -euo pipefail

if [ -z "${JAVA_HOME:-}" ]; then
  echo "JAVA_HOME not set — attempting to continue with system java"
else
  export PATH="$JAVA_HOME/bin:$PATH"
fi

echo "Compiling KamusGimmick..."
mvn -q clean compile
echo "Compile finished."</code></pre>
    </section>

    <footer class="card">
      <p class="note">If you want, I can also generate:
        <ul>
          <li>Badges for this README (build, java version, license)</li>
          <li>Native packages via <code>mvn javafx:jlink</code> / <code>jpackage</code></li>
          <li>A printable PDF README or GitHub-ready README.md</li>
        </ul>
      </p>
      <p class="note">Happy coding — ping me if you want the README adapted for CI (GitHub Actions) or to include screenshots.</p>
    </footer>
  </div>
</body>
</html>
