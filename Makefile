## adapted from https://github.com/abdowns/tsort/blob/a3c1841e558ca474ce92518549ca56437e0ee529/Makefile
## abdowns here, i actually adapted the tsort makefile from the DWM Window Manager makefile so credits to suckless devs

CXX = g++
SRC = src/main.cpp
OBJ = ${SRC:.cpp=.o}
LDFLAGS = -ldpp
PREFIX = /usr/local
NAME = homeworkbot
BINARY = ${NAME}.out

build: ${OBJ}
	${CXX} -o ${BINARY} $^ ${LDFLAGS}

clean:
	rm -f ${OBJ} ${NAME} ${BINARY}

install: build
	mkdir -p ${DESTDIR}${PREFIX}/bin
	install -m 0755 ${BINARY} ${DESTDIR}${PREFIX}/bin/${NAME}

uninstall:
	rm -f ${DESTDIR}${PREFIX}/bin/${BINARY}

.PHONY: clean install uninstall