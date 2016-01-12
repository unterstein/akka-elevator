package elevators

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import elevators.model.{Idle, ElevatorStatus, SystemStatusRequest}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class ElevatorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A Elevator" must {
    "send back a idle behavior if he idles" in {
      val elevatorActor = system.actorOf(Elevator.props(0))
      elevatorActor ! SystemStatusRequest
      expectMsg(ElevatorStatus(0, Idle(0)))
    }
  }

}
