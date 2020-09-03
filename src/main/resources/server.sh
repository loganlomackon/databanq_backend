function startApp {
  cp $2.jar $1.jar
  nohup java -jar -Xmx512m $1.jar > logs/output.txt & echo $! > do_not_delete_$1.pid
}

function stopApp {
  value=`cat do_not_delete_$1.pid`
  kill -9 $value
  rm -fr do_not_delete_$1.pid
  rm logs/output.txt
}

function stopAll {
  echo "Stopping all"
  stopApp databanq
}

function startAll {
  stopAll
  echo "Starting all"
  startApp databanq databanq2
}
build="none"
mode='no_select'

startAll

shift $((OPTIND - 1))