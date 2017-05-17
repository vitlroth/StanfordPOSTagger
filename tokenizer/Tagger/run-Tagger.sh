#!/bin/bash

MYDIR=$(cd $(dirname $0); pwd -P)

# Location of the JAR file of MXPOST
# Replace by the actual path to the JAR file, to avoid having to locate it every time.
#MXPOST_JAR=$(locate -b -n 1 mxpost.jar)
#MXPOST_JAR="/home/rafael/Desktop/UNISINOS2015-2/PARSER/TokenizerOK/jmx/mxpost.jar"
MXPOST_JAR="$MYDIR/jmx/mxpost.jar"

# Model for the POS tagger
MXPOST_MODEL_DIR=$MYDIR/MX-PoST/UTF-8_Model_Cintil_Written

# MXPOST
# Input  : One sentence/line;
# Output : One sentence/line; Underscore ('_') is the tag separator

iconv -f ISO-8859-1 -t UTF-8 -c |
java -cp $MXPOST_JAR -mx30m tagger.TestTagger $MXPOST_MODEL_DIR 2>/dev/null |
sed 's#\([^ ]\+\)_\([^ ]\+\)#\1/\2#g' |
iconv -f UTF-8 -t ISO-8859-1 -c
