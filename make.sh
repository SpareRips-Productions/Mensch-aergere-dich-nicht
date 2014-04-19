#!/bin/sh
echo "Build Documentation"
echo "==================="
./bin/pdfdoclet.sh src/main/java docs/Schnittstellenbeschreibung.pdf de.spareripsproduction
echo "Build Binaries"
echo "=============="
