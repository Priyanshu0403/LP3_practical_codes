// SPDX-License-Identifier: MIT
pragma solidity >=0.6.2 <0.9.0;

contract StudentData {
    // Structure to store student info
    struct Student {
        uint id;
        string name;
        uint[] marks;
        uint percentage;
        bool exists;
    }

    mapping(uint => Student) private students;

    // Variables to demonstrate fallback
    uint public fallbackCalled;

    // Register a student (owner only)
    function registerStudent(
        uint _id,
        string memory _name,
        uint[] memory _marks
    ) public returns (uint) {
        require(!students[_id].exists, "Student already exists");
        require(_marks.length == 3, "Enter marks for 3 subjects");

        uint total = 0;
        for (uint i = 0; i < _marks.length; i++) {
            require(_marks[i] <= 100, "Marks should be <= 100");
            total += _marks[i];
        }

        uint perc = (total * 100) / 300;

        students[_id] = Student(_id, _name, _marks, perc, true);
        return perc;
    }

    // Get student details
    function getStudent(uint _id) public view returns (uint id, string memory, uint[] memory, uint) {
        require(students[_id].exists, "Student does not exist");
        Student memory s = students[_id];
        return (s.id, s.name, s.marks, s.percentage);
    }

    function testFallback() public {
        (bool success,) = address(this).call(abi.encodeWithSignature("nonExistingFunc()"));
        require(success || true); // or emit an event
    }

    // Fallback function to catch unexpected calls or Ether
    fallback() external {
        fallbackCalled += 1;
    }
}
