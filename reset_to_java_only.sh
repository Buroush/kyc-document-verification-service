#!/bin/bash

echo "🚀 Reverting project to pure Spring Boot version..."

PROJECT_DIR=~/Java_Projects/kyc-facematch

cd $PROJECT_DIR || exit

echo "📦 Removing Python AI service (if exists)..."
rm -rf kyc-ai

echo "🧹 Cleaning build artifacts..."
rm -rf target

echo "🔨 Rebuilding project..."
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
  echo "❌ Build failed. Fix errors before continuing."
  exit 1
fi

echo "📝 Updating .gitignore..."
cat > .gitignore <<EOL
# Java
target/
*.log

# OS
.DS_Store

# IDE
.idea/
.vscode/
EOL

echo "📂 Adding changes to Git..."
git add .

echo "💾 Committing changes..."
git commit -m "Reverted AI microservice integration, keeping standalone Spring Boot KYC API"

echo "⬆️ Pushing to GitHub..."
git push

echo "✅ Project successfully reverted and pushed!"
echo "🌍 You can now run:"
echo "   java -jar target/*.jar"
