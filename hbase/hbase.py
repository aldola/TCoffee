class Hbase:
    def read_file(self):
        file=open('../lib/full_lib.txt','r')
        for line in file.read():
            print line


if __name__ == '__main__':
    hbase = Hbase()
    hbase.read_file()
