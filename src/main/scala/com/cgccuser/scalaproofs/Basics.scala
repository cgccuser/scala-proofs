
type _0 = Zero
type _1 = Succ[_0]
case class +[A, B](a: A, b: B)

case class ==[A, B](a: A, b: B)
// type ->[A, B] = A ?=> B
type ForallNats[P[N]] = [N] => () => P[N]

trait Rewrite[T] {
  type Rewritten[Sub, Repl] <: T
  def rewrite[Expr <: T, Sub, Repl](using (Sub == Repl)): (Expr == Rewritten[Sub, Repl])
}

// type Rewrite[Expr, Sub, Repl] = Expr match {
//   case Sub => Repl
//   case Zero => Zero
//   case Succ[a] => Succ[Rewrite[a, Sub, Repl]]
//   case +[a, b] => Rewrite[a, Sub, Repl] + Rewrite[b, Sub, Repl]
//   case _ => Nothing
// }

// def rewrite[Expr, Sub, Repl](using (Sub == Repl)): (Expr == Rewrite[Expr, Sub, Repl]) = null
// def rewriteR[Expr, Sub, Repl](using (Repl == Sub)): (Expr == Rewrite[Expr, Sub, Repl]) = null

given transitivity[A, B, C](using (A == B), (B == C)): (A == C) = null

// //convenience typeclass to turn a type into a runtime value
// trait Value[A] { def apply(): A }
// def valueOf[A](using v: Value[A]): A = v()
// given Value[Zero] = () => Zero
// given [A : Value]: Value[Succ[A]] = () => Succ(valueOf[A])
// given [A : Value, B : Value]: Value[A + B] = () => new +(valueOf[A], valueOf[B])
// given [A : Value, B : Value]: Value[A == B] = () => new ==(valueOf[A], valueOf[B])

// //Just a useless proof that 0 + succ(succ(0)) = succ(succ(0 + 0))
// type SomeRandomProof = _0 + Succ[Succ[_0]] == Succ[Succ[_0 + _0]]
// val someRandomProof: SomeRandomProof = {
//   given s1: (_0 + Succ[Succ[_0]] == Succ[Succ[_0]]) = leftIdentity[Succ[Succ[_0]]]()
//   given s2: (Succ[Succ[_0]] == Succ[Succ[_0 + _0]]) = rewriteR[Succ[Succ[_0]], _0, _0 + _0](using addZero)
//   transitivity[_0 + Succ[Succ[_0]], Succ[Succ[_0]], Succ[Succ[_0 + _0]]]
// }

// println(valueOf[SomeRandomProof])
