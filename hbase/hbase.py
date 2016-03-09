class Hbase:
    def read_file(self):
        file=open('full_lib.txt','r')
        d = dict()
        for line in file.readlines()[6:]:
            if '#' in line:
                seq = self.sequencia(line)
            elif '!' in line:
                break
            else:
                split = line.split()
                comb1 = ''.join([seq[0]," ",split[0]])
                comb2 = ''.join([seq[1]," ",split[1]])

                if comb1 not in d.keys():
                    d[comb1] = [''.join([comb2," ",split[2]])]
                else:
                    d[comb1].append(''.join([comb2," ",split[2]]))
                if comb2 not in d.keys():
                    d[comb2] = [''.join([comb1," ",split[2]])]
                else:
                    d[comb2].append(''.join([comb1," ",split[2]]))

    def sequencia(self,line):
        line = line.translate(None, '#')
        s = line.split()
        return s


if __name__ == '__main__':
    hbase = Hbase()
    hbase.read_file()
