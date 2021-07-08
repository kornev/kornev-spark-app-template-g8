.PHONY: all clean jar test style coverage
.SILENT:    clean jar test style coverage

.DEFAULT: all

all: clean test

clean:
	if [ -d "target"                                     ]; then rm --recursive target;                      fi
	if [ -d "project/target"                             ]; then rm --recursive project/target;              fi
	if [ -d "project/project"                            ]; then rm --recursive project/project;             fi
	if [ `ls -a external/hive | wc -l` -gt 3             ]; then rm --recursive external/hive/*;             fi
	if [ `ls -a external/spark/warehouse | wc -l` -gt 3  ]; then rm --recursive external/spark/warehouse/*;  fi
	if [ `ls -a external/spark/checkpoint | wc -l` -gt 3 ]; then rm --recursive external/spark/checkpoint/*; fi

jar:
	cs launch --jvm adopt:1.8.0-262 sbt -- assembly

test:
	cs launch --jvm adopt:1.8.0-262 sbt -- test

style:
	cs launch --jvm adopt:1.8.0-262 sbt -- scalafix scalafmt

coverage:
	cs launch --jvm adopt:1.8.0-262 sbt -- "set coverageEnabled := true" test coverageReport
