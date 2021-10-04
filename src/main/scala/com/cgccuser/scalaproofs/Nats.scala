sealed trait Nat
case object Zero extends Nat
case class Succ[N <: Nat](n: N) extends Nat


given addZero[A]: (A + _0 == A) = null
given addSucc[A, B]: (A + Succ[B] == Succ[A + B]) = ???

given induction[P[N]](using
    P[_0],
    ForallNats[[N] =>> (P[N] ?=> P[Succ[N]])]
): ForallNats[P] = [N] => () => null.asInstanceOf[P[N]] //just trust me here bro

//Prove 0 + n = n
given leftIdentity: ForallNats[[N] =>> _0 + N == N] = {
  induction[[N] =>> _0 + N == N](using
    addZero[_0],
    [N] => () => { (prev: (_0 + N == N)) ?=>
      transitivity[_0 + Succ[N], Succ[_0 + N], Succ[N]](using addSucc, rewrite[Succ[_0 + N], _0 + N, N])
    }
  )
}
