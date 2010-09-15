# import Repository class and fd module,
from logilab.constraint import *
variables = ('c01','c02','c03','c04','c05','c06','c07','c08','c09','c10')
values = [(room,slot)
          for room in ('room A','room B','room C')
          for slot in ('day 1 AM','day 1 PM','day 2 AM','day 2 PM')]
domains = {}
for v in variables:
    domains[v]=fd.FiniteDomain(values)


constraints = []
for conf in ('c03','c04','c05','c06'):
    constraints.append(fd.make_expression((conf,), "%s[0] == 'room C'" % conf))

for conf in ('c01','c05','c10'):
    constraints.append(fd.make_expression((conf,), "%s[1].startswith('day 1')" % conf))
for conf in ('c02','c03','c04','c09'):
    constraints.append(fd.make_expression((conf,), "%s[1].startswith('day 2')" % conf))

groups = (('c01','c02','c03','c10'),
          ('c02','c06','c08','c09'),
          ('c03','c05','c06','c07'),
          ('c01','c03','c07','c08'))

for g in groups:
    for conf1 in g:
        for conf2 in g:
            if conf2 > conf1:
                constraints.append(fd.make_expression((conf1,conf2),
                                                      '%s[1] != %s[1]'%\
                                                        (conf1,conf2)))

for conf1 in variables:
    for conf2 in variables:
        if conf2 > conf1:
            constraints.append(fd.make_expression((conf1,conf2),
                                                  '%s != %s'%(conf1,conf2)))
r = Repository(variables,domains,constraints)
solutions = Solver().solve(r)
print solutions
