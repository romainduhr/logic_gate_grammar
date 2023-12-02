eq_circuit(in) returns (out)
    s1=in&true;
    out=s1|s1;
end

eq_circuit2(in) returns (out)
    s1=in&true;
    out=s1|s1;
end

eval(eq_circuit2, false)
eval(eq_circuit,true)
